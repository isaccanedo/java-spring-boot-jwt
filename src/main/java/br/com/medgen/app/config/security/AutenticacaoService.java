package br.com.isaccanedo.app.config.security;

import br.com.isaccanedo.app.gateway.database.UsuarioData;
import br.com.isaccanedo.app.gateway.database.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioData> usuarioData = usuarioRepository.findByEmail(username);
        if(usuarioData.isPresent()) {
            return usuarioData.get();
        }
        throw new UsernameNotFoundException("Dados Invalidos");
    }
}
