package org.example.servicereserva.service;

import org.example.servicereserva.entity.Reserva;
import org.example.servicereserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String flightServiceUrl = "http://servicevuelo/flights";

    @Override
    public void realizarReserva(Reserva reserva, int totalPersonas) {
        // Guardar la reserva en la base de datos
        reservaRepository.save(reserva);

        // Actualizar las plazas disponibles en el servicio de vuelos
        webClientBuilder.build()
                .put()
                .uri(flightServiceUrl + "/{id}/reduce/{plazas}", reserva.getVueloId(), totalPersonas)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // Bloquea hasta que se complete la operación
    }
}