package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductFavoriteRecordDto(@NotNull Long id_user,
                                       @NotNull Long id_product) {
}
