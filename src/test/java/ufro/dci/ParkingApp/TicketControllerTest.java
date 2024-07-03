package ufro.dci.ParkingApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
class TicketControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AlmacenarTickets almacenarTickets;

    @Test
    void testGenerarTicket() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        mockMvc.perform(post("/generarTicket"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        List<Ticket> tickets = almacenarTickets.cargarTickets();
        assertFalse(tickets.isEmpty());
    }

    @Test
    void testCalcularPrecio() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        Ticket ticket = new Ticket(LocalDateTime.now(), 1);
        almacenarTickets.guardarTicket(ticket);

        mockMvc.perform(get("/calcularPrecio?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Ticket actualizado = almacenarTickets.encontrarTicketPorId(1);
        assertNotNull(actualizado.getHoraSalida());
        assertTrue(actualizado.getPrecio() > 0);
    }
    @Test
    void testEliminarTicket() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        Ticket ticket = new Ticket(LocalDateTime.now(), 1);
        almacenarTickets.guardarTicket(ticket);

        mockMvc.perform(get("/eliminarTicket?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Ticket eliminado = almacenarTickets.encontrarTicketPorId(1);
        assertNull(eliminado);
    }
}