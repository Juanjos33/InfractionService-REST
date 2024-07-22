package com.bernal.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bernal.infraccionservice.entity.Pago;
import com.bernal.infraccionservice.repository.PagoRepository;
import com.bernal.infraccionservice.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService{

	@Autowired
    private PagoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pago> listAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pago findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	@Transactional
	public Pago create(Pago pago) {
		try {
            return repository.save(pago);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
	}

	@Override
	@Transactional
	public Pago update(int id) { //dni update
	    try {
	        Pago pago = repository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("El id no existe: " + id));
	        pago.setEstado("Anulada");
	        return repository.save(pago);
	    } catch (Exception e) {
	        throw new RuntimeException("Error: " + e.getMessage(), e);
	    }
	}

}
