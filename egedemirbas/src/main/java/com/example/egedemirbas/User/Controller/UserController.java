package com.example.egedemirbas.User.Controller;

import com.example.egedemirbas.User.Converter.UserConverter;
import com.example.egedemirbas.User.Dto.UserDto;
import com.example.egedemirbas.User.Entity.User;
import com.example.egedemirbas.User.Service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/list")
    public List<User> findAll() {
        return userEntityService.findAll();
    }

    //kullanıcıları dönen
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        return userEntityService.findUserById(id);
    }

    //Kullanıcı kaydeden,
    @PostMapping("")
    public ResponseEntity<Object> saveFromDto(@RequestBody UserDto userDto){

        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        user = userEntityService.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    //silen,
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        User user = userEntityService.findUserById(id);
        if(user != null)
            userEntityService.delete(user);
    }
}
