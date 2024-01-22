package com.mycompany.controllers;

import com.mycompany.utilsYServiceGeneral.DtoBuscarEntity;
import com.mycompany.modelos.cita.ServiceFiltrosBusquedaCita;
import com.mycompany.modelos.cita.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private ServiceFiltrosBusquedaCita busquedaCitaService;
    @Autowired
    private ServiceAgendarCita citaService;

/*  @Autowired
    public CitaController(CitaRepository citaRepository, ServiceFiltrosBusquedaCita busquedaCitaService, ServiceAgendarCita citaService){
        this.citaRepository = citaRepository;
        this.busquedaCitaService = busquedaCitaService;
        this.citaService = citaService;
    }
*/
    @PostMapping
    public ResponseEntity<DtoDatosDeCita> registrarCita(@Valid @RequestBody DtoRegistrarCita registrarCita, UriComponentsBuilder uriBuilder){
        //Buscar Masc por id
        DtoDatosDeCita datosCita = citaService.agendarCita(registrarCita);
        URI url = uriBuilder.path("/citas/{id}").buildAndExpand(datosCita.idCita()).toUri();
        return ResponseEntity.created(url).body(datosCita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDatosDeCita> buscarCitaXId(@PathVariable Integer id){
        Cita cita = citaRepository.getReferenceById(id);
        DtoDatosDeCita datosCita = new DtoDatosDeCita(cita);
        return ResponseEntity.ok(datosCita);
    }

    // Se buscan todas las citas activas de todas las Masc:
    @GetMapping
    public ResponseEntity<Page<DtoDatosDeCita>> buscarCitasTodas(@PageableDefault(size = 15, sort = "mascota") Pageable paginacion){
        return ResponseEntity.ok(citaRepository.findByCitaActivaTrue(paginacion).map((DtoDatosDeCita::new)));
    }

    // Este Método está hecho para buscar las Citas activas usando un dato de la Masc y un dato del Due
    @PostMapping("/datos-busqueda")
    public ResponseEntity<List<DtoDatosDeCita>> buscarCitasxFiltrosMascYDue(@Valid @RequestBody DtoBuscarEntity buscarCitas,
                                                              @PageableDefault(size = 15, sort = "fechaYHora") Pageable paginacion){
        return ResponseEntity.ok(busquedaCitaService.buscarCitasActivasDeMascConDue(buscarCitas.getPredicados(), paginacion).map(DtoDatosDeCita::new).toList());
    }

    //Sólo se puede actualizar fechaYHora
    @PutMapping
    @Transactional
    public ResponseEntity<DtoDatosDeCita> actualizarDatosCita(@Valid @RequestBody DtoActualizarDatosCita actualizarCita){
        Cita cita = citaRepository.getReferenceById(actualizarCita.id());
        cita.actualizarCita(actualizarCita);
        return ResponseEntity.ok(new DtoDatosDeCita(cita));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarCita (@PathVariable Integer id){
        Cita cita = citaRepository.getReferenceById(id);
        cita.desactivarCita();
        return ResponseEntity.noContent().build();
    }
}
