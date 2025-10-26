package com.example.TripTrack.controllers;

import com.example.TripTrack.dto.UserDTO;
import com.example.TripTrack.services.ServiceInterfaces.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto)
    {
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(userService.update(dto, id));
    }

    @DeleteMapping("/{id}/deleteUser")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id)
    {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
