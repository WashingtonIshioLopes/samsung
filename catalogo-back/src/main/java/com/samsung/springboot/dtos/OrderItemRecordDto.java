package com.samsung.springboot.dtos;

import com.samsung.springboot.models.OrderModel;
import com.samsung.springboot.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRecordDto(@NotNull Long id_order,
                                 @NotNull Long id_product,
                                 @NotNull BigDecimal quantity) {
}
