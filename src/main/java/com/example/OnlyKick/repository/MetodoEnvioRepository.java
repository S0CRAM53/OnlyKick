package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.MetodoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
}