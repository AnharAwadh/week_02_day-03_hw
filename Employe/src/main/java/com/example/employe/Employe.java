package com.example.employe;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
@AllArgsConstructor
@Data
public class Employe {
        @NotEmpty(message = "id is required")
        @Size(min = 2)
    private String id;
    @NotEmpty(message = "name is required")
    @Size(min = 4,message = "Your name must be 4 ")
    private String name;
    @NotNull(message = "age is required")
    @Positive(message = "the age must be positive")
    @Min(value = 25,message = "Your age must be 25 or more")
    private Integer age;
    private boolean onleave=false;
    @NotNull
    @Pattern(regexp = "\\d{4}")
    private int employmentYear;

    @NotNull
    @Positive
    private Integer annualLeave;

}
