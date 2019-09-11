package com.lambdaschool.crudyrestaurants.repos;

import com.lambdaschool.crudyrestaurants.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>
{

}
