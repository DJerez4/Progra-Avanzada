package ufro.dci.ParkingApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketTest {

    @Test
    void testCreacionTicket() {
        LocalDateTime horaEntrada = LocalDateTime.now();
        Ticket ticket = new Ticket(horaEntrada, 1);
        assertEquals(horaEntrada, ticket.getHoraEntrada());
        assertEquals(1, ticket.getId());
    }

    @Test
    void testSetHoraSalida() {
        LocalDateTime horaEntrada = LocalDateTime.now();
        Ticket ticket = new Ticket(horaEntrada, 1);
        LocalDateTime horaSalida = LocalDateTime.now().plusHours(2);
        ticket.setHoraSalida(horaSalida);
        assertEquals(horaSalida, ticket.getHoraSalida());
    }
}