package com.example.TripTrack.services;

import com.example.TripTrack.dto.UserDTO;
import com.example.TripTrack.entities.User;
import com.example.TripTrack.mappers.UserMapper;
import com.example.TripTrack.repositories.UserRepository;
import com.example.TripTrack.services.ServiceInterfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO dto) {
        return new UserDTO(userRepository.save(new User(dto)));
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> getAll()
    {
        return UserMapper.toDtoList(findAll());
    }

    @Override
    public UserDTO getById(UUID id) {
        return new UserDTO(findById(id));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers(List<User> userList) {
        return UserMapper.toDtoList(userList);
    }

    @Override
    public UserDTO update(UserDTO userDTO, UUID id) {
        User entityToUpdate = findById(id);
        return new UserDTO(userRepository.save(UserMapper.updateEntityFromDto(userDTO,entityToUpdate)));
    }
}
