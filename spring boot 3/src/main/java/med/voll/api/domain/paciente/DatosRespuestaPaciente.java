package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.*;

public record DatosRespuestaPaciente(Long id, String nombre, String documento, String email, String telefono,
                                     DatosDireccion direccion) {
}
