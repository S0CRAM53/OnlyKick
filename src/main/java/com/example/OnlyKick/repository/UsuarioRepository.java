package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    //Metodo para buscar usuario por email
    Optional<Usuario> findByEmail(String email);
}
