package com.k23cnt.kntlab04.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDTO {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3-20 characters")
    @Column(unique = true)
    String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 30, message = "Password must be between 8-30 characters")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-z]).{8,30}", message = "Password must contain at least one letter and one number")
    String password;

    @NotBlank(message = "Fullname cannot be blank")
    @Size(min = 2, max = 50, message = "Fullname must be between 2-30 characters")
    String fullname;

    @Past(message = "Birthday must in the past")
    LocalDate birthday;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email connot be blank")
    @Column(unique = true)
    String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Please enter a valid phone number")
    @NotBlank(message = "Phone number cannot be null")
    String phone;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less or equal 100")
    int age;

    @NotNull(message = "Status cannot be null")
    Boolean status;
}
