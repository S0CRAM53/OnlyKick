package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Direcciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Integer> {
    
    //Metodo para buscar todas las direcciones de un usuario
    List<Direcciones> findByUsuarioIdUsuario(Integer idUsuario);
}