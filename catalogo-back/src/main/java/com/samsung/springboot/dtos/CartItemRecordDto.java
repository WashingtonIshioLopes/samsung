package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemRecordDto(@NotNull Long id_cart,
                                @NotNull Long id_product,
                                @NotNull BigDecimal quantity) {
}
