package com.bernal.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bernal.infraccionservice.entity.Pago;

public interface PagoService {
	List<Pago> listAll(Pageable page);
    Pago findById(int id);
    List<Pago> findByDni(String dni, Pageable page);
    Pago create(Pago pago);
    Pago update(int id);
}
