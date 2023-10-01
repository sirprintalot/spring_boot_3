package med.voll.api.domain.paciente;

import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.*;

public record DatosActualizacionPaciente(@NotNull Long id, String nombre, String documento,
                                         String telefono, String email, DatosDireccion direccion) {
}
