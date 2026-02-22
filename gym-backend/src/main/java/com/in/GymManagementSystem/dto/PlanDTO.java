package com.in.GymManagementSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PlanDTO {
    private Long id;

    @NotBlank(message = "Plan name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Duration is mandatory")
    @Min(value = 1, message = "Duration must be at least 1 month")
    private Integer durationMonths;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    private boolean active;
}
