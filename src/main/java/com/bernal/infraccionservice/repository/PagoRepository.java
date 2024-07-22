package com.bernal.infraccionservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bernal.infraccionservice.entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    List<Pago> findByDniContaining(String dni, Pageable pageable);
}
