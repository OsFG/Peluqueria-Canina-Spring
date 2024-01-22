package com.mycompany.modelos.duenio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Integer>, JpaSpecificationExecutor<Duenio> {
    Page<Duenio> findByActivoTrue(Pageable paginacion);

    //FindByActivoTrue + Specification

    @Query(value = "select d.activo from duenios d where id= :id", nativeQuery = true)
    Boolean findActivoById(@Param("id") Integer id);
}
