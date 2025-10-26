package com.example.TripTrack.dto;

import com.example.TripTrack.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    @Min(12)
    private Integer age;
    @Email(message = "The email should have this format: john@gmail.com")
    private String email;
    private String nickName;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
    }
}
