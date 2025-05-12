package com.example.ReactCRUD.Controller;

import com.example.ReactCRUD.Exception.UserNotFoundException;
import com.example.ReactCRUD.Model.UserModel;
import com.example.ReactCRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository repoobj;

    @PostMapping("/adduser")
    public UserModel newUser(@RequestBody UserModel user)
    {
        return repoobj.save(user);
    }

    @GetMapping("/getuser")
    public List<UserModel> gettingUser()
    {
        return repoobj.findAll();
    }
    @GetMapping("/adduser/{id}")
    UserModel findByID(@PathVariable Long id)
    {
        return repoobj.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }
    @PutMapping("/adduser/{id}")
    UserModel update(@RequestBody UserModel user,@PathVariable Long id)
    {
        return repoobj.findById(id).map(userobj->{
           userobj.setName(user.getName());
           userobj.setUsername(user.getUsername());
           userobj.setEmail(user.getEmail());
           return repoobj.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/adduser/{id}")
    String deleteUser(@PathVariable Long id)
    {
        if(!repoobj.existsById(id))
            throw new UserNotFoundException(id);
        repoobj.deleteById(id);
        return "Object with "+id+" has been deleted";

    }

}
