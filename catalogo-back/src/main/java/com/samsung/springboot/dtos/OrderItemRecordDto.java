package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRecordDto(@NotNull Long id_order,
                                 @NotNull Long id_product,
                                 @NotNull BigDecimal quantity) {
}
