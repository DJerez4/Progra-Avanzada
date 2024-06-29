package ufro.dci.ParkingApp;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlmacenarTickets {
    private static final String NOMBRE_ARCHIVO = "tickets.txt";

    public void guardarTicket(Ticket ticket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            writer.write(String.format("%s;%s;%d;%d%n",
                    ticket.getHoraEntrada().toString(),
                    ticket.getHoraSalida() != null ? ticket.getHoraSalida().toString() : "",
                    ticket.getId(),
                    ticket.getPrecio()));
        } catch (IOException e) {
            e.printStackTrace(); //buena práctica
        }
    }

    public List<Ticket> cargarTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";");
                LocalDateTime horaEntrada = LocalDateTime.parse(partes[0]);
                LocalDateTime horaSalida = !partes[1].isEmpty() ? LocalDateTime.parse(partes[1]) : null;
                int id = Integer.parseInt(partes[2]);
                int precio = Integer.parseInt(partes[3]);
                Ticket ticket = new Ticket(horaEntrada, id);
                ticket.setHoraSalida(horaSalida);
                ticket.setPrecio(precio);
                tickets.add(ticket);
            }
        } catch (IOException e) {
            e.printStackTrace(); //buena práctica
        }
        return tickets;
    }

    public Ticket encontrarTicketPorId(int id) {
        List<Ticket> tickets = cargarTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void actualizarTicket(Ticket ticketActualizado) {
        List<Ticket> tickets = cargarTickets();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Ticket ticket : tickets) {
                if (ticket.getId() == ticketActualizado.getId()) {
                    ticket = ticketActualizado;
                }
                writer.write(String.format("%s;%s;%d;%d%n",
                        ticket.getHoraEntrada().toString(),
                        ticket.getHoraSalida() != null ? ticket.getHoraSalida().toString() : "",
                        ticket.getId(),
                        ticket.getPrecio()));
            }
        } catch (IOException e) {
            e.printStackTrace(); //buena práctica
        }
    }

    public void eliminarTicket(int id) {
        List<Ticket> tickets = cargarTickets();
        tickets.removeIf(ticket -> ticket.getId() == id);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Ticket ticket : tickets) {
                writer.write(String.format("%s;%s;%d;%d%n",
                        ticket.getHoraEntrada().toString(),
                        ticket.getHoraSalida() != null ? ticket.getHoraSalida().toString() : "",
                        ticket.getId(),
                        ticket.getPrecio()));
            }
        } catch (IOException e) {
            e.printStackTrace(); //buena práctica
        }
    }
}
