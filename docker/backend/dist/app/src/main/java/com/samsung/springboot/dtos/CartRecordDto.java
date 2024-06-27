package com.samsung.springboot.dtos;

import com.samsung.springboot.models.UserModel;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartRecordDto(@NotNull Long id_user,
                            @NotNull BigDecimal total) {
}
