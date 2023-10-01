package med.voll.api.domain.paciente;

import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record DatosListadoPaciente(@NotNull @Valid Long id, String nombre, String email, String documento,
                                   String telefono) {

    public DatosListadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono());
    }

}
