package com.karim.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MyOrder")
public class Order {

    //-------------------------Attributes------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private String delivery_method ;

    private String vehicle_type ;

    private String pickup_Location ;

    private String mobile ;

    private String discription ;

    private String client_name ;

    private String address ;

    private double price ;

    private long code ;

    private String payment_method ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date time ;

    private int car ;

    private boolean ok ;

    private String company ;

    private boolean done ;

    @ManyToOne
    private User user ;

    //------------------------------- Constructor-------------------------


    public Order() {
    }

    public Order(String delivery_method , String vehicle_type , String pickup_Location ,
                 String mobile , String description , String client_name , String address , String payment_method , Date time , int car , User user) {
        this.delivery_method = delivery_method ;
        this.vehicle_type = vehicle_type ;
        this.pickup_Location = pickup_Location ;
        this.mobile = mobile ;
        this.discription = description ;
        this.client_name = client_name ;
        this.address = address;
        this.payment_method = payment_method ;
        this.time = time ;
        this.car = car ;
        this.ok = false ;
        this.user = user ;
    }

    //-------------------------------- SetterAndGetter ----------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDelivery_method() {
        return delivery_method;
    }

    public void setDelivery_method(String delivery_method) {
        this.delivery_method = delivery_method;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getPickup_Location() {
        return pickup_Location;
    }

    public void setPickup_Location(String pickup_Location) {
        this.pickup_Location = pickup_Location;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //----------------------ToString---------------------------
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", delivery_method='" + delivery_method + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", pickup_Location='" + pickup_Location + '\'' +
                ", mobile='" + mobile + '\'' +
                ", discription='" + discription + '\'' +
                ", client_name='" + client_name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", code=" + code +
                ", payment_method='" + payment_method + '\'' +
                ", time=" + time +
                ", car=" + car +
                ", ok=" + ok +
                ", company='" + company + '\'' +
                ", done=" + done +
                ", user=" + user +
                '}';
    }
}
