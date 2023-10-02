package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime fecha);
//    Page<Medico> findByActivoTrue(Pageable paginacion);
} 
