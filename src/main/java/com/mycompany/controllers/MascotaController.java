package com.mycompany.controllers;

import com.mycompany.utilsYServiceGeneral.DtoBuscarEntity;
import com.mycompany.modelos.mascota.ServiceFiltrosBusquedaMascota;
import com.mycompany.modelos.mascota.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private ServiceFiltrosBusquedaMascota busquedaMascotaService;
/*    @Autowired
    public MascotaController(MascotaRepository mascotaRepository, ServiceFiltrosBusquedaMascota busquedaMascotaService){
        this.mascotaRepository = mascotaRepository;
        this.busquedaMascotaService=busquedaMascotaService;
    }

*/
    @PostMapping
    public ResponseEntity<DtoDatosDeMascota> registrarMascota(@Valid @RequestBody DtoRegistrarDatosMascota registroMascota, UriComponentsBuilder uriBuilder){

        Mascota mascota = busquedaMascotaService.registrarMascota(registroMascota);
        DtoDatosDeMascota datosDeMascota = new DtoDatosDeMascota(mascota);
        URI url = uriBuilder.path("/mascotas/{id}").buildAndExpand(mascota.getId()).toUri();
        return ResponseEntity.created(url).body(datosDeMascota);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDatosDeMascota> buscarMascotaXId(@PathVariable Integer id){
        Mascota mascota = mascotaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoDatosDeMascota(mascota));
    }

    @GetMapping
    public ResponseEntity<Page<DtoDatosDeMascota>> buscarMascotasTodas(@PageableDefault(size = 15, sort = "nombreMasc")Pageable paginacion){
        return ResponseEntity.ok(mascotaRepository.findByActivoTrue(paginacion).map(DtoDatosDeMascota::new));
    }


    /* Este Método está pensado para recibir máximo 2 datos:
        El primero de la Mascota
        El segdundo del Duenio      */
    @PostMapping("/datos-busqueda")
    public ResponseEntity<Page<DtoDatosDeMascota>> buscarMascotaXFiltros(@Valid @RequestBody DtoBuscarEntity buscarEntityMascota,
                                                                         @PageableDefault(size = 15, sort = "nombreMasc")Pageable paginacion){
        Specification<Mascota> filtros = busquedaMascotaService.buscarConFiltroYJoin(buscarEntityMascota.getPredicados());
        return ResponseEntity.ok(mascotaRepository.findAll(filtros, paginacion).map(DtoDatosDeMascota::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DtoDatosDeMascota> actualizarMascota(@Valid @RequestBody DtoActualizarDatosMascota actualizarMascota){
        Mascota mascota = mascotaRepository.getReferenceById(actualizarMascota.id());
        mascota.actualizarDatos(actualizarMascota);
        return ResponseEntity.ok(new DtoDatosDeMascota(mascota));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarMascota(@PathVariable Integer id){
        Mascota mascota = mascotaRepository.getReferenceById(id);
        mascota.desactivarMascota();
        return ResponseEntity.noContent().build();
    }
}
