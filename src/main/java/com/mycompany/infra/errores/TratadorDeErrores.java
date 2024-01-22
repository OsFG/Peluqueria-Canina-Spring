package com.mycompany.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    // Excepción al no encontrar una Entidad existente con los datos brindados
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    // Excepción al enviar datos no validos para el registro + DTO que campura c/Columna + Mensaje de error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(CampoDeErrores::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
        public record CampoDeErrores(String campo, String error) {
            public CampoDeErrores(FieldError errores) {
                this(errores.getField(), errores.getDefaultMessage());
            }
        }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionesDeIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());

        // Excepciones de Negocio (@Component) / Excepciones de cualquier tipo en general (no contempladas)
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacion(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
