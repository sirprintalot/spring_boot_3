package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;


    public void validar(DatosAgendarConsulta datos){

        if(datos.idMedico() == null){
            throw new ValidacionDeIntegridad("");
        }
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());

        if(medicoConConsulta){
            throw new ValidacionDeIntegridad("Este medico ya tiene una cita agendada.");
        }
    }

}
