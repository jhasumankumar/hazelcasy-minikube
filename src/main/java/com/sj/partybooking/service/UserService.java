package com.sj.partybooking.service;

import com.sj.partybooking.domain.UserModel;
import com.sj.partybooking.hazalcast.cache.manager.DistributedCacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(path = "/cache", produces = "application/json")
public class UserService {

    @Autowired
    private DistributedCacheManager cacheManager;

    @GetMapping("/user/{email}")
    public ResponseEntity<?> findById(@PathVariable(value = "email") String email) {
        ConcurrentMap<String, UserModel> cache = cacheManager.getCache("store");
        UserModel userModel = cache.get(email);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(userModel != null) {
            return new ResponseEntity<>(userModel, httpHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/user/{username}/{email}")
    public ResponseEntity<?> createUserModel(
            @PathVariable("username") String username, @PathVariable("email") String email ) {
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setFirstName(username);
        userModel.setLastName(username);
        ConcurrentMap<String, UserModel> cache = cacheManager.getCache("store");
        cache.put(userModel.getEmail(), userModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(email, httpHeaders, HttpStatus.CREATED);
    }
}
