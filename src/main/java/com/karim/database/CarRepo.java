package com.karim.database;

import com.karim.model.Car;
import com.karim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car , Long>{
    List<Car> findByUser(User user);
}
