package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartRecordDto(@NotNull Long idUser, @NotNull BigDecimal total) {
}
