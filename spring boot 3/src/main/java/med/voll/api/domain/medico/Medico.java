package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.*;
import org.hibernate.proxy.*;

import java.util.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosDeRegistroMedico datosDeRegistroMedico) {

        this.activo = true;
        this.nombre = datosDeRegistroMedico.nombre();
        this.email = datosDeRegistroMedico.email();
        this.documento = datosDeRegistroMedico.documento();
        this.especialidad = datosDeRegistroMedico.especialidad();
        this.direccion = new Direccion(datosDeRegistroMedico.direccion());
        this.telefono = datosDeRegistroMedico.telefono();
    }


    public void actualizarDatos(DatosActualizacionMedico datosActualizacionMedico) {
        if (datosActualizacionMedico.nombre() != null) {
            this.nombre = datosActualizacionMedico.nombre();
        }
        if (datosActualizacionMedico.documento() != null) {
            this.documento = datosActualizacionMedico.documento();
        }
        if (datosActualizacionMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizacionMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Medico medico = (Medico) o;
        return getId() != null && Objects.equals(getId(), medico.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
