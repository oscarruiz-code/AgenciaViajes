package org.example.servicevuelo.repository;

import org.example.servicevuelo.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}