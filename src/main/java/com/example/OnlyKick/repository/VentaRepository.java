package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
    //Metodo para ver el historial de compras de un usuario
    List<Venta> findByUsuarioIdUsuario(Integer idUsuario);
}