package com.mycompany.modelos.duenio;

import com.mycompany.utilsYServiceGeneral.DtoPredicadoDeBusqueda;
import com.mycompany.utilsYServiceGeneral.FiltrosDeBusquedaService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFiltrosBusquedaDuenio extends FiltrosDeBusquedaService<Duenio> {

    /*
        Hay que hacer modificaciones al código de los Services para búsquedas de Duenio y Mascota:
            se debe tener un Método que llame a una implementación de Specification en caso de que se quiera
            usar los datos de nombre del Duenio para buscarlo, algo como:
                if(columna == nombreCompleto){
	                Specification.Join
	                toArray[0]}
	            if(columna != nombre completo){
	                Specification
	                toArray[1]

	                etc. = toArray.add()
     */

}
