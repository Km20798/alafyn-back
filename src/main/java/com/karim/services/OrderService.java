package com.karim.services;

import com.karim.database.OrderRepo;
import com.karim.model.Order;
import com.karim.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo ;

    public List<Order> getAllOrders(){
        return repo.findAll();
    }

    public Order findByCode(long code){
        return repo.findByCode(code);
    }

    public Order SaveOrder(Order order){
        return repo.save(order);
    }

    public List<Order> findByUser(User user){
        return repo.findByUser(user);
    }

//    public List<Order> findByTime(Date from , Date to){
//        return repo.findAllByTimeBetween(from , to);
//    }
}
