package com.k23cnt.kntlab04.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class KhoaDTO {
    @NotBlank(message = "Makh cannot be empty")
    @Size(min = 2, message = "Id must be at least 2 characters")
    String Makh;

    @NotBlank(message = "Tenkh cannot be empty")
    @Size(min = 5, max = 25, message = "Name must be between 5-25 character")
    String Tenkh;
}
