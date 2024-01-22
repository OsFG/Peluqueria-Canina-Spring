package com.mycompany.modelos.cita;

import com.mycompany.modelos.duenio.DuenioRepository;
import com.mycompany.modelos.mascota.MascotaRepository;
import com.mycompany.utilsYServiceGeneral.DtoPredicadoDeBusqueda;
import com.mycompany.utilsYServiceGeneral.FiltrosDeBusquedaService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceFiltrosBusquedaCita{
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DuenioRepository duenioRepository;
    @Autowired
    private MascotaRepository mascotaRepository;


    // Se buscan las citas activas x datos Masc y Due:
    public Page<Cita> buscarCitasActivasDeMascConDue(List<DtoPredicadoDeBusqueda> predicados, Pageable paginacion){
        Specification<Cita> buscarMascYDue = buscarMascotaDuenioYCitaActiva(predicados);
        return citaRepository.findAll(buscarMascYDue, paginacion);
    }
    public Specification<Cita> buscarMascotaDuenioYCitaActiva(List<DtoPredicadoDeBusqueda> predicados){
        return (root, cq, cb) -> {
            List<Predicate> filtrosArray = new ArrayList<>();
            DtoPredicadoDeBusqueda datosMasc = predicados.get(0);
            Predicate predicadoM = cb.equal(root.join("mascota").get(datosMasc.getColumna().toString()), datosMasc.getValor().toString());
            filtrosArray.add(predicadoM);

            DtoPredicadoDeBusqueda datosDue = predicados.get(1);
            Predicate predicadoD = cb.equal(root.join("mascota").join("duenio").get(datosDue.getColumna().toString()), datosDue.getValor().toString());
            filtrosArray.add(predicadoD);

            Predicate predicadoCitaActiva = cb.equal(root.get("citaActiva"), true);
            filtrosArray.add(predicadoCitaActiva);
            return cb.and(filtrosArray.toArray(new Predicate[0]));
        };
    }

}
