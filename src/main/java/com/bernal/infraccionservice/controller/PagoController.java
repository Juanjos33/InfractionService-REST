package com.bernal.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bernal.infraccionservice.entity.Pago;
import com.bernal.infraccionservice.service.PagoService;

@RestController
@RequestMapping(value = "api/v1/pagos")
public class PagoController {
	@Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<Pago>> findAll(
            @RequestParam(value = "name", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {

    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Pago> mantenimientos;

        if (dni != null && !dni.isEmpty()) {
            mantenimientos = service.findByDni(dni, pageable);
        } else {
            mantenimientos = service.listAll(pageable);
        }

        if (mantenimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mantenimientos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable("id") int id) {
        Pago pago = service.findById(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }
    
    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<Pago>> findByUsuario(@PathVariable("dni") String dni) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        List<Pago> pagos = service.findByDni(dni, pageable);
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }
    
    @PostMapping
    public ResponseEntity<Pago> create(@RequestBody Pago pago) {
    	try {
            Pago nuevoPago = service.create(pago);
            return ResponseEntity.ok(nuevoPago);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable("id") int id) {
        try {
            Pago pagoActualizado = service.update(id);
            return ResponseEntity.ok(pagoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
