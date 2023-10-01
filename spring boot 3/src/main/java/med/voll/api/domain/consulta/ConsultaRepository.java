package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
//    Page<Medico> findByActivoTrue(Pageable paginacion);
} 
