package med.voll.api.domain.paciente;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByDadoDeAltaFalse(Pageable paginacion);


    @Query("""
            select p.dadoDeAlta
            from Paciente p
            where p.id=:idPaciente
            """)
    boolean findActivoById(Long idPaciente);
}
