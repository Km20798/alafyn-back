package com.karim.model;

import com.karim.model.Address;

import javax.persistence.*;

@Entity
public class User {

    //--------------------- Attributes ----------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private String username ;

    private String email ;

    private String phone ;

    private String password ;

    @Embedded
    private Address address ;

    private String role ;

    private int active ;
    
    private boolean card ;

    //--------------------- Constructors -----------------
    public User() {
    }

    public User(String username , String email , String phone , String password , Address address){
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.address=address;
        this.role="ROLE_USER";
        this.active=1;
    }

    //----------------------- Setter And Getter -------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    public boolean isCard() {
		return card;
	}

	public void setCard(boolean card) {
		this.card = card;
	}

	//----------------------- toString -------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
