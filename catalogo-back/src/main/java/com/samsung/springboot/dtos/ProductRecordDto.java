package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String description,
                               @NotNull BigDecimal price,
                               @NotNull Long IdUnit,
                               @NotNull Long IdCategory,
                               @NotNull BigDecimal weight) {
}
