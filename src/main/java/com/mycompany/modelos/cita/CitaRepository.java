package com.mycompany.modelos.cita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>, JpaSpecificationExecutor<Cita> {

    Page<Cita> findByCitaActivaTrue(Pageable paginacion);

    @Query(value = "select c.cita_activa from citas c where c.id_mas= :idMas and DATE(c.cita_fyh)= :fecha", nativeQuery = true)
    List<Cita> buscarCitaActivaXFechaYMascota(Integer idMas, LocalDate fecha);

    Boolean existsByFechaYHora(LocalDateTime citaFYH);
}
