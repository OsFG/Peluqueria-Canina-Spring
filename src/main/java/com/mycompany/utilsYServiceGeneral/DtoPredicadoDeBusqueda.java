package com.mycompany.utilsYServiceGeneral;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPredicadoDeBusqueda {
        @Valid
        private Object columna;
        private Object valor;

}

