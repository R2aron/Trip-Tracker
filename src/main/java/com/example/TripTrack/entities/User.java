package com.example.TripTrack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NotNull
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String CNP;

    @Email(message = "Format de email invalid")
    @NotBlank(message = "Emailul este obligatoriu")
    @Column(nullable = false, unique = true)
    private String email;

    //password;
    private String nickName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_trips",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )

    private Set<Trip> trips = new HashSet<>();
}
