package com.in.GymManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerDTO {
    private Long id;

    @NotBlank(message = "Trainer name is mandatory")
    private String name;

    private String specialization;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 10, message = "Phone number must be  10 digits")
    private String phone;

    private Integer memberCount;
}
