package com.Web.User.Controller;

import com.Web.User.entity.User;
import com.Web.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.getUserById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/n/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable String email){
        if(userService.getUserByEmail(email).get().size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.getUserByEmail(email).get(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.UpdateUser(id,user));
        }catch (RuntimeException exception){
          return   ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
