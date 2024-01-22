package com.mycompany.modelos.cita;

import com.mycompany.infra.errores.ValidacionDeIntegridad;
import com.mycompany.modelos.cita.validaciones.ValidacionAgendarCita;
import com.mycompany.modelos.duenio.DuenioRepository;
import com.mycompany.modelos.mascota.Mascota;
import com.mycompany.modelos.mascota.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAgendarCita {

    private CitaRepository citaRepository;
    private MascotaRepository mascotaRepository;
    private DuenioRepository duenioRepository;
    private List<ValidacionAgendarCita> validaciones;

    @Autowired
    public ServiceAgendarCita(CitaRepository citaRepository, MascotaRepository mascotaRepository,
                              DuenioRepository duenioRepository, List<ValidacionAgendarCita> validaciones){
        this.citaRepository=citaRepository;
        this.mascotaRepository=mascotaRepository;
        this.duenioRepository=duenioRepository;
        this.validaciones=validaciones;
    }

    public DtoDatosDeCita agendarCita(DtoRegistrarCita datosCita){
        if(!mascotaRepository.findById(datosCita.idMascota()).isPresent()){
            throw new ValidacionDeIntegridad("No hay registro de Mascota con esos datos");
        }
        if(!duenioRepository.findById(datosCita.idDuenio()).isPresent()){
            throw new ValidacionDeIntegridad("No hay registro de Dueño con esos datos");
        }

        validaciones.forEach(v-> v.validarDatos(datosCita));

        Mascota mascota = mascotaRepository.findById(datosCita.idMascota()).get();
        var cita = new Cita(datosCita.citaFYH(), mascota);
        citaRepository.save(cita);
        DtoDatosDeCita citaAgendada = new DtoDatosDeCita(cita);

        return citaAgendada;

    }
}
