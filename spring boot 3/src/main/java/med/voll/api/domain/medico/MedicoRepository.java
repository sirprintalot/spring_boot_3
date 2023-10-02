package med.voll.api.domain.medico;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
             select m from Medico m
              where m.activo= true
              and
              m.especialidad=:especialidad
              and
              m.id not in(
                select c.medico.id from Consulta c
                where
                c.fecha=:fecha
              )
              order by rand()
              limit 1
            """)

    Medico seleccionarMedicoPorEspecialidad(Especialidad especialidad, LocalDateTime fecha);


    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);
} 
