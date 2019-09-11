package com.lambdaschool.crudyrestaurants.repos;

import com.lambdaschool.crudyrestaurants.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long>
{
    Agent findByName(String name);

}
