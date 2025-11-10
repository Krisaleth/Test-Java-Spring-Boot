package com.k23cnt.kntlab04.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    @NotBlank(message = "Fullname cannot be empty")
    @Size(min = 3, max = 25, message = "Fullname must be between 3-25 characters")
    String fullName;

    @NotBlank(message = "Gender cannot be empty")
    String gender;

    @Range(min = 18, max = 60, message = "Age must be between 18-60 years old")
    @NotBlank(message = "Age cannot be empty")
    int age;

    @Min(1)
    @NotBlank(message = "Salary cannot be empty")
    Long salary;
}
