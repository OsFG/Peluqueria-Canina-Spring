package com.mycompany.modelos.mascota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>, JpaSpecificationExecutor<Mascota> {

    Page<Mascota> findByActivoTrue(Pageable pageable);

    @Query(value= "select m.activo from mascotas m where idMascota= :id", nativeQuery = true)
    Boolean findActivoById(Integer id);
}
