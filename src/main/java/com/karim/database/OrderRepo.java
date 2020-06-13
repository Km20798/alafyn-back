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
    Order findByCode(long code);
    List<Order> findByUser(User user);
    Order findById(long id);
    List<Order> findByCompany(String company);
}
