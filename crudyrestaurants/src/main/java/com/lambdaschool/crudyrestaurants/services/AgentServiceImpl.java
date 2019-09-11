package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Agent;
import com.lambdaschool.crudyrestaurants.models.Customer;
import com.lambdaschool.crudyrestaurants.repos.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value="agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    private AgentRepository agentrepos;

    @Override
    public List<Agent> findAll() {
        List<Agent> rtnList = new ArrayList<>();
        agentrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Agent findAgentById(long id) {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id " + id));
    }

    @Override
    public Agent findAgentByName(String name) {

        Agent agent = agentrepos.findByName(name);

        if (agent == null)
        {
            throw new EntityNotFoundException("Agent Not Found " + name);
        }
        return agent;
    }

    @Override
    public void delete(long id) {
        if (agentrepos.findById(id).isPresent())
        {
            agentrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Id " + id);
        }

    }

    @Override
    public Agent save(Agent agent) {
        Agent newAgent = new Agent();

        newAgent.setAgentname(agent.getAgentname());
        newAgent.setWorkingarea(agent.getWorkingarea());
        newAgent.setCommission(agent.getCommission());
        newAgent.setPhone(agent.getPhone());
        newAgent.setCountry(agent.getCountry());


        for (Customer m : agent.getCustomers())
        {
            newAgent.getCustomers().add(new Customer(m.getCustname(), m.getCustcity(), m.getWorkingarea(), m.getCustcountry(), m.getGrade(), m.getOpeningamt(), m.getReceiveamt(), m.getPaymentamt(), m.getOutstandingamt(), m.getPhone(), newAgent));
        }

        return agentrepos.save(newAgent);
    }


    @Transactional
    @Override
    public Agent update(Agent agent, long id) {
        Agent currentAgent = agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (agent.getAgentname() != null)
        {
            currentAgent.setAgentname(agent.getAgentname());
        }

        if (agent.getWorkingarea() != null)
        {
            currentAgent.setWorkingarea(agent.getWorkingarea());
        }

        if (agent.getCommission() != 0)
        {
            currentAgent.setCommission(agent.getCommission());
        }

        if (agent.getPhone() != null)
        {
            currentAgent.setPhone(agent.getPhone());
        }

        if (agent.getCountry() != null)
        {
            currentAgent.setCountry(agent.getCountry());
        }

        if (agent.getCustomers().size() > 0)
        {
            for (Customer m : agent.getCustomers())
            {
                currentAgent.getCustomers().add(new Customer(m.getCustname(), m.getCustcity(), m.getWorkingarea(), m.getCustcountry(), m.getGrade(), m.getOpeningamt(), m.getReceiveamt(), m.getPaymentamt(), m.getOutstandingamt(), m.getPhone(), currentAgent));

            }
        }

        return agentrepos.save(currentAgent);
    }


}
