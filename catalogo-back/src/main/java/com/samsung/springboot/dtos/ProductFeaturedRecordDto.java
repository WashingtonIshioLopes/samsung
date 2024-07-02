package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductFeaturedRecordDto(@NotNull Long id_product) {
}
