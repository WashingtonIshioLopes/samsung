package com.samsung.springboot.dtos;

import com.samsung.springboot.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRecordDto(@NotNull Long id_user,
                             @NotNull BigDecimal total) {
}
