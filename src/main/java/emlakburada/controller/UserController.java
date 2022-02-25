package emlakburada.controller;

import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.User;
import emlakburada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllAdvert() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/users" )
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users")
    public ResponseEntity<HttpStatus> deleteAllUsers(){
      userService.deleteAllUsers();
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
