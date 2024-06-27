package com.samsung.springboot.dtos;

import com.samsung.springboot.models.CategoryModel;
import com.samsung.springboot.models.UnitModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductRecordDto(@NotBlank String description,
                               @NotNull BigDecimal price,
                               @NotNull Long id_unit,
                               @NotNull Long id_category,
                               @NotNull BigDecimal weight
                               ) {
}
