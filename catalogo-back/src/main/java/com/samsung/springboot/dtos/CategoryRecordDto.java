package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRecordDto(@NotBlank String description, @NotBlank String code) {
}
