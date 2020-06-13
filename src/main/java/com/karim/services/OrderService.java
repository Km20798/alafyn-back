package com.karim.services;

import com.karim.database.OrderRepo;
import com.karim.model.Order;
import com.karim.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Order findById(long id){
        return repo.findById(id);
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

    public List<Order> findByCompany(String email){
        return repo.findByCompany(email);
    }

    public Order updateOrder(long id , Order updateOrder){
        Order order = findById(id);
        if(order == null)
            return null;
        order.setId(updateOrder.getId());
        order.setDelivery_method(updateOrder.getDelivery_method());
        order.setVehicle_type(updateOrder.getVehicle_type());
        order.setPickup_Location(updateOrder.getPickup_Location());
        order.setMobile(updateOrder.getMobile());
        order.setDiscription(updateOrder.getDiscription());
        order.setClient_name(updateOrder.getClient_name());
        order.setAddress(updateOrder.getAddress());
        order.setPrice(updateOrder.getPrice());
        order.setCode(updateOrder.getCode());
        order.setPayment_method(updateOrder.getPayment_method());
        order.setTime(updateOrder.getTime());
        order.setCar(updateOrder.getCar());
        order.setUser(updateOrder.getUser());
        order.setOk(updateOrder.isOk());
        order.setCompany(updateOrder.getCompany());
        order.setDone(updateOrder.isDone());
        return repo.saveAndFlush(order);
    }

    public void deleteOrder(long id){
        Order order = findById(id);
        if(order != null)
            repo.deleteById(id);
    }
}
