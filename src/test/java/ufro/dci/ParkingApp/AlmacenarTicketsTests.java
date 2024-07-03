package ufro.dci.ParkingApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlmacenarTicketsTests {

    @Autowired
    private AlmacenarTickets almacenarTickets;

    @Test
    void testGuardarTicket() {
        Ticket ticket = new Ticket(LocalDateTime.now(), 1);
        almacenarTickets.guardarTicket(ticket);
        List<Ticket> tickets = almacenarTickets.cargarTickets();
        assertNotNull(tickets.contains(ticket));
    }
    @Test
    void testCargarTickets() {
        List<Ticket> tickets = almacenarTickets.cargarTickets();
        assertNotNull(tickets);
    }
}