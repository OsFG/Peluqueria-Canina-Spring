package com.mycompany.modelos.mascota;

import com.mycompany.infra.errores.ValidacionDeIntegridad;
import com.mycompany.modelos.duenio.Duenio;
import com.mycompany.modelos.duenio.DuenioRepository;
import com.mycompany.utilsYServiceGeneral.DtoPredicadoDeBusqueda;
import com.mycompany.utilsYServiceGeneral.FiltrosDeBusquedaService;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceFiltrosBusquedaMascota extends FiltrosDeBusquedaService{

    private DuenioRepository duenioRepository;
    private MascotaRepository mascotaRepository;

    public ServiceFiltrosBusquedaMascota(MascotaRepository mascotaRepository, DuenioRepository duenioRepository){
        this.mascotaRepository=mascotaRepository;
        this.duenioRepository=duenioRepository;
    }

    public Duenio buscarDuenioDeMascconFiltro(List<DtoPredicadoDeBusqueda> filtros){
        Duenio duenio = null;
        Specification<Duenio> predicadoDuenio = super.generarFiltros(filtros);
        duenio = duenioRepository.findOne(predicadoDuenio).get();
        if(!duenio.getActivo()){
            throw new ValidacionDeIntegridad("No se puede registrar una Mascota con Dueño inactivo");
        }
        return duenio;
    }
    public Mascota registrarMascota(DtoRegistrarDatosMascota datosMascota){
        Mascota mascota = new Mascota(datosMascota, buscarDuenioDeMascconFiltro(datosMascota.filtroDatoDuenio().getPredicados()));
        mascotaRepository.save(mascota);
        return mascota;
    }

    public Specification<Mascota> buscarConFiltroYJoin(@Valid List<DtoPredicadoDeBusqueda> filtros){
        return (root, cq, cb) -> {
            List<Predicate> filtrosArray = new ArrayList<>();
            DtoPredicadoDeBusqueda datosMasc = filtros.get(0);
            Predicate predicadoM = cb.equal(root.get(datosMasc.getColumna().toString()), datosMasc.getValor().toString());
            filtrosArray.add(predicadoM);

            DtoPredicadoDeBusqueda datosDue = filtros.get(1);
            Predicate predicadoD = cb.equal(root.join("duenio").get(datosDue.getColumna().toString()), datosDue.getValor().toString());
            filtrosArray.add(predicadoD);
            return cb.and(filtrosArray.toArray(new Predicate[0]));

            // Agregar filtro de Duenio Activo?
        };
    }

}
