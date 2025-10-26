package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.UserDTO;
import com.example.TripTrack.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
    public UserDTO save(UserDTO userDTO);
    public UserDTO getById(UUID id);
    public List<User> findAll();
    public List<UserDTO> getAll();
    public User findById(UUID id);
    public void deleteById(UUID id);
    public List<UserDTO> getAllUsers(List<User> userList);
    public  UserDTO update(UserDTO userDTO, UUID id);
}
