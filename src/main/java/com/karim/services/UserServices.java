package com.karim.services;

import com.karim.database.UserRepo;
import com.karim.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServices {

    @Autowired
    private UserRepo repo ;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setActive(1);
        return repo.save(user);
    }

    public User updateUser(String email , User updateUser){
        User user = getUser(email);
        if(user == null)
            return null ;
        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
        user.setPhone(updateUser.getPhone());
        user.setPassword(passwordEncoder().encode(updateUser.getPassword()));
        user.setRole(updateUser.getRole());
        user.setActive(updateUser.getActive());
        user.setAddress(updateUser.getAddress());
        return repo.saveAndFlush(user);
    }

    public List<User> getAllUser(){
        return repo.findAll();
    }

    public User getUser(String email) {
        return repo.findByEmail(email);
    }


    public User findByEmail(String email){
        return repo.findByEmail(email);
    }

    public User findByRole(String role){
        return repo.findByRole(role);
    }

    public User findByPhone(String phone){
        return repo.findByPhone(phone);
    }

    public void deleteByEmail(long id){
        repo.deleteById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public User findByEmailAndPassword(String email , String password){
        return repo.findByEmailAndPassword(email , password);
    }

    public User findByPhoneAndPassword(String phone, String password) {
        return repo.findByPhoneAndPassword( phone ,  password);
    }

    public List<User> findByRoleAndCountry(String country){
        return repo.findByRoleAndAddress_Country("ROLE_STORE",country);
    }

}
