package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRecordDto(@NotNull Long id_user,
                             @NotNull Long id_ckeckout,
                             @NotNull BigDecimal total,
                             @NotBlank String status) {
}
