package org.example.servicevuelo.controller;

import org.example.servicevuelo.entity.Vuelo;
import org.example.servicevuelo.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class VueloController {

    @Autowired
    private VueloRepository vueloRepository;

    // Obtener todos los vuelos
    @GetMapping
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    // Crear un nuevo vuelo
    @PostMapping
    public Vuelo createVuelo(@RequestBody Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    // Obtener un vuelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable Long id) {
        Optional<Vuelo> vuelo = vueloRepository.findById(id);
        return vuelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un vuelo por ID
    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long id, @RequestBody Vuelo vueloDetails) {
        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);
        if (optionalVuelo.isPresent()) {
            Vuelo vuelo = optionalVuelo.get();
            vuelo.setCompania(vueloDetails.getCompania());
            vuelo.setFecha(vueloDetails.getFecha());
            vuelo.setPrecio(vueloDetails.getPrecio());
            vuelo.setPlazasDisponibles(vueloDetails.getPlazasDisponibles());
            Vuelo updatedVuelo = vueloRepository.save(vuelo);
            return ResponseEntity.ok(updatedVuelo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un vuelo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);
        if (optionalVuelo.isPresent()) {
            vueloRepository.delete(optionalVuelo.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Reducir plazas disponibles en un vuelo
    @PutMapping("/{id}/reduce/{plazas}")
    public ResponseEntity<Void> reducirPlazas(@PathVariable Long id, @PathVariable int plazas) {
        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);
        if (optionalVuelo.isPresent()) {
            Vuelo vuelo = optionalVuelo.get();
            vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazas);
            vueloRepository.save(vuelo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
