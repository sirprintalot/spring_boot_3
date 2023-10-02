package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.*;
import med.voll.api.infra.errores.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datos) {

        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();

        var diferenciaDe30Min = Duration.between(ahora, horaDeConsulta).toMinutes() < 30;

        if (diferenciaDe30Min) {
            throw new ValidacionDeIntegridad("Las consultas tienen que ser programadas con un minimo de 30 minutos de" +
                    " anticipacion.");
        }
    }
}
