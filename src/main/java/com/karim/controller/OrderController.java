package com.karim.controller;

import com.karim.model.Order;
import com.karim.model.User;
import com.karim.services.OrderService;
import com.karim.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService ;
    @Autowired
    private UserServices userServices ;

//-------------------------- getAllOrders -------------------------------
    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
    }

//--------------------------------- findOrderByCode ---------------------------
    @GetMapping("/orders/{email}/{code}")
    public ResponseEntity<Order> getOrder(@PathVariable long code , @PathVariable String email){
        Order order = orderService.findByCode(code);
        User user = userServices.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Order>(order , HttpStatus.OK);
    }

//---------------------------------- get Orders For User -----------------------
    @GetMapping("orders/{email}")
    public ResponseEntity<List<Order>> getOrder(@PathVariable String email) {
        User user = userServices.findByEmail(email);
        List<Order> orders = orderService.findByUser(user);
        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
    }

//----------------------------- add new Order ---------------------------------
    @PostMapping("/orders/{email}")
    public ResponseEntity<Order> getOrder(@RequestBody Order order , @PathVariable String email){
        User user = userServices.findByEmail(email);
        if(order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        order.setUser(user);
        orderService.SaveOrder(order);
        return new ResponseEntity<Order>(order , HttpStatus.OK);
    }

//-------------------------------------- find By User And Date -------------------
    @GetMapping("/orders/{email}/{from}/{to}")
    public ResponseEntity<List<Order>> findByTime(@PathVariable String email , @PathVariable String from , @PathVariable String to)throws Exception{
        User user = userServices.findByEmail(email);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<Order> orders = orderService.findByUser(user);
        if(orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<Order> orderTimes = new ArrayList<>();
        for (Order order:orders) {
            if(
                    (new SimpleDateFormat("yyyy-MM-dd").parse(order.getTime().toString()).after(new SimpleDateFormat("yyyy-MM-dd").parse(from)) ||
                    new SimpleDateFormat("yyyy-MM-dd").parse(order.getTime().toString()).equals(new SimpleDateFormat("yyyy-MM-dd").parse(from)))&&
                            (new SimpleDateFormat("yyyy-MM-dd").parse(order.getTime().toString()).before(new SimpleDateFormat("yyyy-MM-dd").parse(to)) ||
                                    new SimpleDateFormat("yyyy-MM-dd").parse(order.getTime().toString()).equals(new SimpleDateFormat("yyyy-MM-dd").parse(to)))
            ){
                orderTimes.add(order);
            }
        }
        return new ResponseEntity<List<Order>>(orderTimes , HttpStatus.OK);
    }

//---------------------------------------------- update order ----------------------------------------------
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id , @RequestBody Order updateOrder){
        Order order = orderService.findById(id);
        if(order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        System.out.println(updateOrder.getCompany());
        orderService.updateOrder(id , updateOrder);
        System.out.println(updateOrder.getCompany());
        return new ResponseEntity<Order>(order , HttpStatus.OK);
    }

//---------------------------------------------- Delete Order ----------------------------------------------
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id){
        Order order = orderService.findById(id);
        if(order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//--------------------------------------------- find by company ---------------------------------------
    @GetMapping("/orders/stores/{email}")
    public ResponseEntity<List<Order>> findByCompany(@PathVariable String email){
        List<Order> orders = orderService.findByCompany(email);
        if(orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
    }

//----------------------------------------------------------------------
}
