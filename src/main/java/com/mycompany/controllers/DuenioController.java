package com.mycompany.controllers;

import com.mycompany.utilsYServiceGeneral.DtoBuscarEntity;
import com.mycompany.modelos.duenio.*;
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
@RequestMapping("/duenios")
public class DuenioController {
    @Autowired
    private DuenioRepository duenioRepository;
    @Autowired
    private ServiceFiltrosBusquedaDuenio filtrosBusquedaDuenio;
/*    @Autowired
    public DuenioController (DuenioRepository duenioRepository, FiltrosDeBusquedaService<Duenio> filtrosDeBusqueda){
        this.duenioRepository = duenioRepository;
        this.filtrosDeBusqueda = filtrosDeBusqueda;
    }
*/
    @PostMapping
    public ResponseEntity<DtoDatosDeDuenio> registrarDuenio(@Valid @RequestBody DtoRegistrarDatosDuenio datosRegistroDuenio,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Duenio duenio = duenioRepository.save(new Duenio(datosRegistroDuenio));
        DtoDatosDeDuenio datosDeDuenio = new DtoDatosDeDuenio(duenio.getIdDuenio(), duenio.getNombreCompleto(), duenio.getTelefono(), duenio.getCorreo(),
                                                              duenio.getDireccion());
        URI url = uriComponentsBuilder.path("/duenios/{id}").buildAndExpand(duenio.getIdDuenio()).toUri();
        return ResponseEntity.created(url).body(datosDeDuenio);
    }

/*Cambiar:
    Acceso a "Direccion" solo a Adm. y al Usuario dueño de los datos
*/ @GetMapping("/{id}")
    public ResponseEntity<DtoDatosDeDuenio> buscarDuenioxId(@PathVariable Integer id){
        Duenio duenio = duenioRepository.getReferenceById(id);
        DtoDatosDeDuenio datosDeDuenio = new DtoDatosDeDuenio(duenio);
        return ResponseEntity.ok(datosDeDuenio);
    }

/*Cambiar:
    Acceso a "Duenios" solo a Adm.
*/  @GetMapping
    public ResponseEntity<Page<DtoDatosDeDuenio>> listadoDuenios(@PageableDefault(size = 15, sort = "nombreCompleto") Pageable paginacion){
        return ResponseEntity.ok(duenioRepository.findByActivoTrue(paginacion).map(DtoDatosDeDuenio::new));
                                                                     // -->Un Duenio m para c/new DtoDatosDeDuenio(m)
    }

    @PostMapping("/datos-busqueda")
    public ResponseEntity<Page<DtoDatosDeDuenio>> buscarDuenioxDato(@Valid @RequestBody DtoBuscarEntity buscarEntityDuenio,
                                                                    @PageableDefault(size = 15) Pageable paginacion){
        Specification<Duenio> filtros = filtrosBusquedaDuenio.generarFiltros(buscarEntityDuenio.getPredicados());
        return ResponseEntity.ok(duenioRepository.findAll(filtros, paginacion).map(DtoDatosDeDuenio::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DtoDatosDeDuenio> actualizarDuenio(@Valid @RequestBody DtoActualizarDatosDuenio actualizarDatosDuenio){
        Duenio duenio = duenioRepository.getReferenceById(actualizarDatosDuenio.id());
        duenio.actualizarDatos(actualizarDatosDuenio);
        return ResponseEntity.ok(new DtoDatosDeDuenio(duenio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarDuenio(@PathVariable Integer id){
        Duenio duenio = duenioRepository.getReferenceById(id);
        duenio.desactivar();
        return ResponseEntity.noContent().build();
    }
}
