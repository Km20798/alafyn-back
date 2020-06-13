package com.karim.model;

import javax.persistence.*;

@Entity
public class Car {

//    Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private String type ;

    private String driver ;

    private String phone ;

    @ManyToOne
    private User user ;

//    constructor
    public Car() {
    }

    public Car(String type , String driver , String phone , User user){
        this.type = type ;
        this.driver = driver ;
        this.phone = phone ;
        this.user = user ;
    }

//    Setter And Getter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
