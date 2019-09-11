package com.lambdaschool.crudyrestaurants.repos;

import com.lambdaschool.crudyrestaurants.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    Customer findByName(String name);
}
