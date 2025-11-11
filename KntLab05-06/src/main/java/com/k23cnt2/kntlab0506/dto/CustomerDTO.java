package com.k23cnt2.kntlab0506.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {
    Long id;
    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 8, max = 25, message = "Username must be between 8-25 characters!")
    String username;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 8, max = 25, message = "Password must be between 8-25 characters!")
    String password;

    @NotBlank(message = "Fullname cannot be empty!")
    @Size(min = 5, max = 30, message = "Fullname must be between 5-30 characters!")
    String fullname;

    @NotBlank(message = "Address cannot be empty!")
    @Size(min = 15, max = 100, message = "Address must be between 15-100 characters!")
    String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+/[0-9. ()-]{7,25}$", message = "Invalid number! Please enter another one!")
    String phoneNumber;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Invalid email! Please enter another one!")
    @Size(min = 10, max = 25, message = "Email must be between 10-25 characters")
    String email;

    @Past(message = "Birthday must be in the past")
    LocalDate birthday;

    @NotNull(message = "Status cannot be null!")
    Boolean active;

}
