package med.voll.api.domain.paciente;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByDadoDeAltaFalse(Pageable paginacion);
}
