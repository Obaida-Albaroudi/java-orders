package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Order;
import com.lambdaschool.crudyrestaurants.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository ordersrepos;

    @Override
    public List<Order> findAll() {
        List<Order> rtnList = new ArrayList<>();
        ordersrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Order findOrderById(long id) {
        return ordersrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id " + id));
    }

    @Override
    public void delete(long id) {
        if (ordersrepos.findById(id).isPresent())
        {
            ordersrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Id " + id);
        }

    }

    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrddescription(order.getOrddescription());
        newOrder.setCustomer(order.getCustomer());

        return ordersrepos.save(newOrder);

    }

    @Override
    public Order update(Order order, long id) {
        Order currentOrder = ordersrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (order.getOrdamount() != 0)
        {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if (order.getAdvanceamount() != 0)
        {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }
        if (order.getOrddescription() != null)
        {
            currentOrder.setOrddescription(order.getOrddescription());
        }
        if (order.getCustomer() != null)
        {
            currentOrder.setCustomer(order.getCustomer());
        }

        return ordersrepos.save(currentOrder);
    }
}
