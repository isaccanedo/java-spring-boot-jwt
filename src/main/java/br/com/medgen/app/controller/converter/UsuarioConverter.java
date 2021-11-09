package br.com.isaccanedo.app.controller.converter;

import br.com.isaccanedo.app.entity.Usuario;
import br.com.isaccanedo.app.gateway.database.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioData convert(Usuario usuario){
        UsuarioData usuarioData = new UsuarioData();
        usuarioData.setEmail(usuario.getEmail());
        usuarioData.setSenha(usuario.getSenha());
        return usuarioData;
    }

}
