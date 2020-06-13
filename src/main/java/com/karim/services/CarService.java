package com.karim.services;

import com.karim.database.CarRepo;
import com.karim.model.Car;
import com.karim.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepo repo ;

    public List<Car> getAllCars(){
        return repo.findAll();
    }

    public List<Car> findByUser(User user){
        return repo.findByUser(user);
    }

    public Car getCar(long id){
        Optional<Car> myCar = repo.findById(id);
        return myCar.get();
    }

    public void deleteCar(long id){
        repo.deleteById(id);
    }

    public Car addCar(Car car){
        return repo.save(car);
    }


}
