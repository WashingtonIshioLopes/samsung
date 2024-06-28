package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record PaymentTypeRecordDto(@NotBlank String name,
                                   @NotBlank String type) {
}
