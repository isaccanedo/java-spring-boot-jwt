package br.com.isaccanedo.app.gateway.database.repository;

import br.com.isaccanedo.app.gateway.database.UsuarioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioData, Integer> {

    Optional<UsuarioData> findByEmail(String email);

}
