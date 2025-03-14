package org.example.servicereserva.controller;

import org.example.servicereserva.entity.Reserva;
import org.example.servicereserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    // Obtener todas las reservas
    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    // Crear una nueva reserva
    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Actualizar una reserva por ID
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.setNombreUsuario(reservaDetails.getNombreUsuario());
            reserva.setDni(reservaDetails.getDni());
            reserva.setHotelId(reservaDetails.getHotelId());
            reserva.setVueloId(reservaDetails.getVueloId());
            Reserva updatedReserva = reservaRepository.save(reserva);
            return ResponseEntity.ok(updatedReserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una reserva por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            reservaRepository.delete(optionalReserva.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}