package com.samsung.springboot.dtos;

import com.samsung.springboot.models.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductImageRecordDto(@NotNull Long id_product,
                                    @NotBlank String image) {
}
