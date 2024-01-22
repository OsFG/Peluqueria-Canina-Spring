package com.mycompany.modelos.cita;

import com.mycompany.modelos.mascota.Mascota;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "cita")
@Table(name = "citas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of ="id")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCita;

    @Column(name = "cita_fyh")
    private LocalDateTime fechaYHora;

    @Column(name = "cita_activa")
    private Boolean citaActiva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mas")
    private Mascota mascota;

    public Cita(LocalDateTime fyh, Mascota mascota) {
        this.fechaYHora = fyh;
        this.mascota = mascota;
        this.citaActiva = true;
    }

    /*   public Cita(DtoRegistrarCita registrarCita){
           this.citaFYH = registrarCita.citaFYH();
           this.mascota = registrarCita.mascota();
           this.citaActiva = true;
       }
   */

    public void actualizarCita(DtoActualizarDatosCita datosCita){
        this.fechaYHora = datosCita.citaFYH();
    }
    public void desactivarCita() {
        this.citaActiva= false;
    }
}
