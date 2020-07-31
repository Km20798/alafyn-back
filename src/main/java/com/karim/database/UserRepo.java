package com.karim.database;

import com.karim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	// get List of Users By Role And Country
    List<User> findByRoleAndAddress_Country(String role ,  String contry);
    // get User By Email
    User findByEmail(String email);
    // get User By Phone
    User findByPhone(String phone);
    // get User By ROle
    User findByRole(String role);
    // get User By Email And Password
    User findByEmailAndPassword(String email , String password);
    // get User By Phone And Password
    User findByPhoneAndPassword(String phone, String password);
    // delete User By Email
    void deleteByEmail(String Email);

}
