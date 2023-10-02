package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){

        if(datos.idMedico() == null){
            return;
        }

        var medicoActivo =  medicoRepository.findActivoById(datos.idMedico());

        if(!medicoActivo){
            throw new ValidacionDeIntegridad("No se puede agendar una cita con un medico inactivo en el sistema");
        }
    }
}
