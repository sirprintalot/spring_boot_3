package med.voll.api.domain.medico;

import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.*;

public record DatosActualizacionMedico(@NotNull Long id, String nombre, String documento,
                                       String email, String telefono,  DatosDireccion direccion) {
    
}
