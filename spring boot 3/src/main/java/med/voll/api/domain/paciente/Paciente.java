package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.*;
import org.hibernate.proxy.*;

import java.util.*;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente){
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento = datosRegistroPaciente.documento();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatosPaciente(DatosActualizacionPaciente datosActualizacionPaciente){
        if (datosActualizacionPaciente.nombre() != null){
            this.nombre = datosActualizacionPaciente.nombre();
        }
        if (datosActualizacionPaciente.documento() != null){
            this.documento = datosActualizacionPaciente.documento();
        }
        if (datosActualizacionPaciente.telefono() != null){
            this.telefono = datosActualizacionPaciente.telefono();
        }
        if (datosActualizacionPaciente.email() != null){
            this.email = datosActualizacionPaciente.email();
        }
        if(datosActualizacionPaciente.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizacionPaciente.direccion());
        }
    }

    public void desactivarPaciente() {
            this.activo = true;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Paciente paciente = (Paciente) o;
        return getId() != null && Objects.equals(getId(), paciente.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
