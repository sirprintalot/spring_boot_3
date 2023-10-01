package med.voll.api.domain.medico;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.*;

public record DatosDeRegistroMedico(@NotBlank
                                    String nombre,

                                    @NotBlank
                                    @Email
                                    String email,

                                    @NotBlank
                                    @Pattern(regexp = "\\d{6,8}")
                                    String telefono,

                                    @NotBlank
                                    @Pattern(regexp = "\\d{4,6}")
                                    String documento,

                                    @NotNull 
                                    Especialidad especialidad,

                                    @NotNull
                                    @Valid
                                    DatosDireccion direccion) {

}
