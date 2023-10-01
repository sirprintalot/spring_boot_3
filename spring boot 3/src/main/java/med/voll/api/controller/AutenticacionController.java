package med.voll.api.controller;

import jakarta.validation.*;
import med.voll.api.domain.usuarios.*;
import med.voll.api.infra.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService  tokenService;

    

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid  DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
//        var authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
//                datosAutenticacionUsuario.clave());
         var usuarioAutenticado = manager.authenticate(authToken);
         var JWToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWToken));

    }
    
}
