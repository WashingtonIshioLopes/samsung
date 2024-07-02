package com.samsung.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonAddressRecordDto(@NotNull Long id_user,
                                     @NotBlank String endereco,
                                     @NotBlank String bairro,
                                     @NotBlank String cep,
                                     @NotBlank String cidade,
                                     @NotBlank String estado) {
}
