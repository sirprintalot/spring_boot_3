package med.voll.api.domain.consulta;

import java.time.*;

public record DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
}
