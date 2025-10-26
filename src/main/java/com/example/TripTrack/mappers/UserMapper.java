package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.dto.UserDTO;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserDTO> toDtoList(List<User> userList)
    {
        if(userList == null || userList.isEmpty())
            return List.of();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO(user);
                    return  userDTO;
                }).collect(Collectors.toList());
        return userDTOList;
    }

    public static List<User> usersListFromDtos(List<UserDTO> dtoList)
    {
        if(dtoList == null || dtoList.isEmpty())
            return List.of();
        List<User> userList = dtoList.stream()
                .map(userDTO -> {
                    User user  = new User(userDTO);
                    return user;
                }).collect(Collectors.toList());
        return userList;
    }

    public static User updateEntityFromDto(UserDTO userDTO,User entityToUpdate)
    {
        entityToUpdate.setFirstName(userDTO.getFirstName());
        entityToUpdate.setLastName(userDTO.getLastName());
        entityToUpdate.setAge(userDTO.getAge());
        entityToUpdate.setEmail(userDTO.getEmail());
        entityToUpdate.setNickName(userDTO.getNickName());
        return entityToUpdate;
    }
}
