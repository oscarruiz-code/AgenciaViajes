package org.example.servicehotel.controller;

import org.example.servicehotel.entity.Hotel;
import org.example.servicehotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    // Obtener todos los hoteles
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Crear un nuevo hotel
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Obtener un hotel por ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un hotel por ID
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setNombre(hotelDetails.getNombre());
            hotel.setCategoria(hotelDetails.getCategoria());
            hotel.setPrecio(hotelDetails.getPrecio());
            hotel.setDisponible(hotelDetails.getDisponible());
            Hotel updatedHotel = hotelRepository.save(hotel);
            return ResponseEntity.ok(updatedHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un hotel por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotelRepository.delete(optionalHotel.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}