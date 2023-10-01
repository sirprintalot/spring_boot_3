package med.voll.api.domain.consulta;

import jakarta.validation.constraints.*;
import med.voll.api.domain.medico.*;

import java.time.*;

public record DatosAgendarConsulta(Long id, @NotNull Long idPaciente, Long idMedico,
                                   @NotNull @Future LocalDateTime fecha, Especialidad especialidad) {
}
