package com.karim.controller;

import com.karim.jwt.resource.JwtResponse;
import com.karim.jwt.resource.signinRequest;
import com.karim.model.User;
import com.karim.security.TokenUtil;
import com.karim.security.UserDetail;
import com.karim.services.UserServices;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private TokenUtil tokenUtil ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private UserServices userServices ;
    @Autowired
    private AuthenticationManager manager ;
    @Autowired
    private UserDetail service ;

    //----------------------signin to get token --------------------------------
        @PostMapping("/sigin")
        public JwtResponse signin(@RequestBody signinRequest request){
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = service.loadUserByUsername(request.getUsername());
            String token = tokenUtil.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(token);
            return jwtResponse;
        }

    //---------------------- Get All Users ------------------------
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userServices.getAllUser();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //---------------------- Get User By Email ----------------------
    @GetMapping("/users/{email}")
    public ResponseEntity<User> getUserById(@PathVariable String email) {
        User user = userServices.findByEmail(email);
        if (user == null) {
            user = userServices.findByPhone("email");
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //---------------------- Create new User ----------------------
    @PostMapping("/users/reg")
    public ResponseEntity<User> getUserById(@Valid @RequestBody User user) {
        User sbe = userServices.findByEmail(user.getEmail());
        if (sbe != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User sbp = userServices.findByPhone(user.getPhone());
        if (sbp != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        userServices.createUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //---------------------- update User By Id ---------------------
    @PutMapping("/users/{email}")
    public ResponseEntity<User> updateUserById(@PathVariable String email , @Valid @RequestBody User user) {
        User currentUser = userServices.getUser(email);
        if (currentUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userServices.updateUser(email , user);
        return new ResponseEntity<User>(userServices.getUser(email), HttpStatus.OK);
    }

    //----------------------- delete User By Id -----------------------
    @DeleteMapping("/users/{email}")
    public ResponseEntity<?> deleteUserById(@PathVariable String email ) {
        User user = userServices.getUser(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userServices.deleteByEmail(user.getId());
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    //------------------------ delete All User ------------------------
    @DeleteMapping("/users")
    public ResponseEntity<?> deleteAllUser() {
        List<User> users = userServices.getAllUser();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        userServices.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //----------------------- Login By Email and Password ----------------
    @GetMapping("/users/{email}/{password}")
    public ResponseEntity<User> findByEmailAndPassword(@PathVariable String email , @PathVariable String password){
        User user = userServices.findByEmailAndPassword(email , password);
        if (user == null) {
            user = userServices.findByPhoneAndPassword(email, password);
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user , HttpStatus.OK);
    }

    //----------------------------------- Find User By Place and Role-------------------------
    @GetMapping("/users/store/{country}")
    public ResponseEntity<List<User>> findByRoleAndCountry(@PathVariable String country){
        List<User> users = userServices.findByRoleAndCountry(country);
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<User>>(users , HttpStatus.OK);
    }

    //-----------------------------------------------------------------------------------------
}