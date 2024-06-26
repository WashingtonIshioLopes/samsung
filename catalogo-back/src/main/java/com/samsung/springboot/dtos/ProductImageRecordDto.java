package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductImageRecordDto(@NotNull Long IdProduct, @NotBlank String image) {
}
