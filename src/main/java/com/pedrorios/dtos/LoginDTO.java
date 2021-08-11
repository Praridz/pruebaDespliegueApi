package com.pedrorios.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {

    @NotNull(message = "El usuario no puede ser null")
    @NotEmpty(message = "El usuario no puede ser vacío")
    private String correo;

    @NotNull(message = "La contraseña no puede ser null")
    @NotEmpty(message = "La contraseña no puede ser vacía")
    private String password;
}
