package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class HorariosDeFuncionamiento implements ValidadorDeConsultas{

    
    public void validar(DatosAgendarConsulta datos){

        var antesDeApertura = datos.fecha().getHour() < 7 ;

        var despuesDeCierre = datos.fecha().getHour() > 19;

        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        if(domingo || antesDeApertura || despuesDeCierre){
            throw new ValidacionDeIntegridad("El horario de atencion de la clinica es de lunes a sabado de 07:00 a " +
                    "19:00 hrs.");
        }
    }
}
