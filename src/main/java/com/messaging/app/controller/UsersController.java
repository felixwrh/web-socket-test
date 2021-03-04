package com.messaging.app.controller;

import com.messaging.app.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UsersController {

    @Autowired private SimpUserRegistry simpUserRegistry;

    public Set<SimpUser> getUsers() {
        return simpUserRegistry.getUsers();
    }

    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName){
        System.out.println("Handling register user request: " + userName);

        try{
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/connections")
    public String connectedEquipments() {
        return this.simpUserRegistry
                .getUsers().toString();
    }
    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll(){
        return UserStorage.getInstance().getUsers();
    }
}
