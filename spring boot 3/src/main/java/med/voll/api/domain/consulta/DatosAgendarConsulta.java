package med.voll.api.domain.consulta;

import jakarta.validation.constraints.*;
import med.voll.api.domain.medico.*;
import org.springframework.stereotype.*;

import java.time.*;

public record DatosAgendarConsulta(

        @NotNull
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad) {
}