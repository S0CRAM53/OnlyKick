package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Integer> {
}