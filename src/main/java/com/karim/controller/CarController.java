package com.karim.controller;

import com.karim.model.Car;
import com.karim.model.User;
import com.karim.services.CarService;
import com.karim.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CarController {

    @Autowired
    private CarService carService ;
    @Autowired
    private UserServices userServices ;

//-----------------------------------------    get All Car for User     -------------------------
    @GetMapping("/cars/{email}")
    public ResponseEntity<List<Car>> getAllCarForUser(@PathVariable String email){
        User user = userServices.findByEmail(email);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Car> cars = carService.findByUser(user);
        if(cars.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Car>>(cars , HttpStatus.OK);
    }

//----------------------------------------   Get Car By Id    ----------------------------------
    @GetMapping("/cars/{email}/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable String email , @PathVariable long id){
        User user = userServices.findByEmail(email);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Car car = carService.getCar(id);
        if(car == null || car.getUser() != user)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Car>(car , HttpStatus.OK);
    }

//----------------------------------------    Add New Car     ----------------------------------
    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity<Car>(car , HttpStatus.OK);
    }

//---------------------------------------    deleteById       ------------------------------------
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        Car car = carService.getCar(id);
        if(car == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
