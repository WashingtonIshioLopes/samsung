package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CheckoutRecordDto(@NotBlank String code,
                                @NotNull Long id_user,
                                @NotNull Long id_cart,
                                @NotNull Long id_payment,
                                @NotBlank String status) {
}
