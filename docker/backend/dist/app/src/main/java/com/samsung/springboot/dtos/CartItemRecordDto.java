package com.samsung.springboot.dtos;

import com.samsung.springboot.models.CartModel;
import com.samsung.springboot.models.UserModel;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemRecordDto(@NotNull Long id_cart,
                                @NotNull Long id_product,
                                @NotNull BigDecimal quantity) {
}
