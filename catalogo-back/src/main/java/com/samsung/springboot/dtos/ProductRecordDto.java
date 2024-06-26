package com.samsung.springboot.dtos;

import com.samsung.springboot.models.CategoryModel;
import com.samsung.springboot.models.UnitModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String description,
                               @NotNull BigDecimal price,
                               @NotNull UnitModel IdUnit,
                               @NotNull CategoryModel IdCategory,
                               @NotNull BigDecimal weight) {
}
