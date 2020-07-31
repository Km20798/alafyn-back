package com.karim.database;

import com.karim.model.Order;
import com.karim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order , Long> {
	// get List of Orders By Code 
    Order findByCode(long code);
    // get List of Orders By Users
    List<Order> findByUser(User user);
    // get Order By Id
    Order findById(long id);
    // get List Of Order By Company
    List<Order> findByCompany(String company);
}
