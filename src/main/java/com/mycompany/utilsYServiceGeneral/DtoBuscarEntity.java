package com.mycompany.utilsYServiceGeneral;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoBuscarEntity {
    @Valid
    private List<DtoPredicadoDeBusqueda> predicados;
}
