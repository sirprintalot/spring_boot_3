package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.*;
import med.voll.api.domain.paciente.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos){

        if(datos.idPaciente() == null){
                return;
        }

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteActivo){
            throw new ValidacionDeIntegridad("No se puede agendar una cita con un paciente inactivo en el sistema");
        }
    }
}
