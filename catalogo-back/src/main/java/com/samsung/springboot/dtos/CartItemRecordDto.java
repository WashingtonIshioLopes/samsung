package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemRecordDto(@NotNull Long idCarter, @NotNull Long idUser, @NotNull BigDecimal quantity) {
}
