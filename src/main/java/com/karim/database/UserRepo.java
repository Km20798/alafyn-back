package com.karim.database;

import com.karim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByRoleAndAddress_Country(String role ,  String contry);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByRole(String role);
    User findByEmailAndPassword(String email , String password);
    User findByPhoneAndPassword(String phone, String password);
    void deleteByEmail(String Email);

}
