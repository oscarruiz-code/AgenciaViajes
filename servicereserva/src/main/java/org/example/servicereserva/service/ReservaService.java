package org.example.servicereserva.service;

import org.example.servicereserva.entity.Reserva;

public interface ReservaService {
    void realizarReserva(Reserva reserva, int totalPersonas);
}