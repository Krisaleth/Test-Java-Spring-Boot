package com.k23cnt.kntlab04.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonHocDTO {
    @NotBlank(message = "Mamh cannot be empty")
    @Max(value = 2, message = "Mamh only contains 2 characters")
    String Mamh;

    @NotBlank(message = "Tenmh cannot be empty")
    @Size(min = 5, max = 50, message = "Tenmh must be between 5-50 characters")
    String Tenmh;

    @NotBlank(message = "Sotiet cannot be empty")
    @Range(min=45, max=75)
    int Sotiet;
}
