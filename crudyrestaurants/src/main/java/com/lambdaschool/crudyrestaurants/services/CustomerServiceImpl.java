package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Customer;
import com.lambdaschool.crudyrestaurants.models.Order;
import com.lambdaschool.crudyrestaurants.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository custrepos;

    @Override
    public List<Customer> findAll() {
        List<Customer> rtnList = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Customer findCustomerById(long id) {
        return custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id " + id));
    }

    @Override
    public Customer findCustomerByName(String name) {
        Customer customer = custrepos.findByName(name);

        if (customer == null)
        {
            throw new EntityNotFoundException("Customer Not Found " + name);
        }
        return customer;
    }

    @Override
    public void delete(long id) {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Id " + id);
        }

    }

    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOpeningamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        for (Order o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getOrddescription()));
        }

        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getOpeningamt() != 0)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.getPaymentamt() != 0)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.getOutstandingamt() != 0)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getOrders().size() > 0)
        {
            for (Order o : customer.getOrders())
            {
                currentCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getOrddescription()));
            }
        }

        return custrepos.save(currentCustomer);
    }
}
