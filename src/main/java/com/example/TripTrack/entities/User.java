package com.example.TripTrack.entities;

import com.example.TripTrack.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NotNull
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String nickName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;

    public User(UserDTO dto)
    {
        this.id = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.age = dto.getAge();
        this.email = dto.getEmail();
        this.nickName = dto.getNickName();
    }

}
