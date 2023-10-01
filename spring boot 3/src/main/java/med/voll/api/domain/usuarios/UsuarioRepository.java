package med.voll.api.domain.usuarios;

import org.springframework.data.jpa.repository.*;
import org.springframework.security.core.userdetails.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
