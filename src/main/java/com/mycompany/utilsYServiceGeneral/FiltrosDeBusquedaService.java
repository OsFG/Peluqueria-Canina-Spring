package com.mycompany.utilsYServiceGeneral;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class FiltrosDeBusquedaService<T> {

    public Specification<T> generarUnFiltro(@Valid DtoPredicadoDeBusqueda predicado){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(predicado.getColumna().toString()), predicado.getValor().toString());
            }
        };
    }
    public Specification<T> generarFiltros(@Valid List<DtoPredicadoDeBusqueda> predicados){
        return (root, cq, cb) -> {
            List<Predicate> filtrosArray = new ArrayList<>();
            for (DtoPredicadoDeBusqueda predicadoCriteria : predicados){
                Predicate filtro = cb.equal(root.get(predicadoCriteria.getColumna().toString()), predicadoCriteria.getValor().toString());
                filtrosArray.add(filtro);
            }
            return cb.and(filtrosArray.toArray(new Predicate[0]));
        };
    }
}
