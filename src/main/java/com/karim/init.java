package com.karim;

import com.karim.model.Message;
import com.karim.model.Order;
import com.karim.services.MessageService;
import com.karim.model.Address;
import com.karim.model.User;
import com.karim.services.OrderService;
import com.karim.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Date;

@Configuration
@Service
public class init implements CommandLineRunner {

    @Autowired
    private MessageService messageService ;
    @Autowired
    private UserServices userServices;
    @Autowired
    private OrderService orderService ;

    @Override
    public void run(String... args) throws Exception {
//    	createAdmin();
    }

    public void createAdmin(){
        Address address = new Address( "Gharbia" , "El Santa" , "Meet Haway El Santa Gharbia");
        User user = new User("Admin" , "admin@gmail.com" , "01012856931" , "admin123" , address);
        user.setRole("ROLE_ADMIN");
        userServices.createUser(user);
    }

    public void createMessage(){
        Message message = new Message("Karim Mohamed" , "kmaged207@gmail.com" , "Welcome");
        messageService.addMessage(message);
    }

    public void createUser(){
        Address address = new Address( "Tanta" , "Egypt" , "12MeetHaway Santa Tanta");
        User user = new User("Karim Mohamed" , "kmaged207@gmail.com" , "01117631429" , "karim123" , address);
        user.setRole("ROLE_USER");
        userServices.createUser(user);
        User ahmed = new User("Ahmed Zaid" , "ahmed@gmail.com" , "01012856931" , "ahmed123" , address);
        ahmed.setRole("ROLE_ADMIN");
        userServices.createUser(ahmed);
        User mustafa = new User("Mustafa Mohamed" , "mostafa@gmail.com" , "01012056931" , "mustafa123" , address);
        mustafa.setRole("ROLE_STORE");
        userServices.createUser(mustafa);
        Order order = new Order("delivery" , "jumbo" , "tanta" , "01117631429" , "my order " , "karim" , "santa" , "cash" , new Date("20/7/2020"), 2 , user);
        order.setPrice(2000);
        order.setCode(1287);
        orderService.SaveOrder(order);
    }

}
