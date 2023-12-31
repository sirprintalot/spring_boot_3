package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validaciones.*;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.paciente.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agenda(DatosAgendarConsulta datos) {

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("este id de paciente no fue encontrado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("este id de medico no fue encontrado");
        }

        //validaciones
        validadores.forEach(v -> v.validar(datos));
         
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);

        if(medico == null){
            throw new ValidacionDeIntegridad("No hay medicos disponibles");
        }

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("debes seleccionar una especialidad");
        }
        return medicoRepository.seleccionarMedicoPorEspecialidad(datos.especialidad(), datos.fecha());
    }
}
                                                                        