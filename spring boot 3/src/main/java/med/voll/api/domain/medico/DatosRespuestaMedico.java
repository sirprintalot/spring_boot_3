package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.*;

public record DatosRespuestaMedico(Long id, String nombre, String documento, String email, String telefono,
                                   Especialidad especialidad,
                                   DatosDireccion direccion){
}
