package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoVentaRepository extends JpaRepository<EstadoVenta, Integer> {
}