package med.voll.api.controller;

import jakarta.transaction.*;
import jakarta.validation.*;
import med.voll.api.domain.direccion.*;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.*;

import java.net.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosDeRegistroMedico datosDeRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {

        Medico medico = medicoRepository.save(new Medico(datosDeRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getDocumento(),
                medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    @GetMapping
    public ResponseEntity <Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 9) Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);listar todos los medicos
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));//listar solo los activos
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizacionMedico datosActualizacionMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizacionMedico.id());
        medico.actualizarDatos(datosActualizacionMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getDocumento(),
                medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/{id}")  hard delete from database
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }

//creamos este medtodo para devover los datos de un solo medico basados en el id
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getDocumento(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        
        return ResponseEntity.ok(datosMedico);
    }



    
}
