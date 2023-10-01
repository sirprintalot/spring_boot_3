package med.voll.api.domain.medico;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.time.*;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);


    @Query("""
            select m from Medico m
            where m.activo=1 and\s
            m.especialidad = especialidad and
            m.id not in(
            select c.medico.id from consulta c
            c.data = fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoPorEspecialidad(Especialidad especialidad, LocalDateTime fecha);
} 
