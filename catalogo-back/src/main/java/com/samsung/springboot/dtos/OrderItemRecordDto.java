package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRecordDto(@NotNull Long idOrder, @NotNull Long idUser, @NotNull BigDecimal quantity) {
}
