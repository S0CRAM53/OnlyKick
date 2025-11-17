package com.example.OnlyKick.repository;

import com.example.OnlyKick.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
    
    //Metodo para filtrar comunas por region
    List<Comuna> findByRegionIdRegion(Integer idRegion);
}