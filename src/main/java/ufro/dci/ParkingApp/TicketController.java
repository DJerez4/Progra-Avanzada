package ufro.dci.ParkingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private AlmacenarTickets almacenarTickets;

    @GetMapping("/")
    public String index(Model model) {
        List<Ticket> tickets = almacenarTickets.cargarTickets();
        model.addAttribute("tickets", tickets);
        return "index";
    }

    @PostMapping("/generarTicket")
    public String generarTicket(Model model) {
        int nuevoId = almacenarTickets.cargarTickets().size() + 1;
        Ticket nuevoTicket = new Ticket(LocalDateTime.now(), nuevoId);
        almacenarTickets.guardarTicket(nuevoTicket);
        List<Ticket> tickets = almacenarTickets.cargarTickets();
        model.addAttribute("tickets", tickets);
        return "redirect:/";
    }

    @GetMapping("/calcularPrecio")
    public String calcularPrecio(@RequestParam("id") int id, Model model) {
        Ticket ticket = almacenarTickets.encontrarTicketPorId(id);
        LocalDateTime horaSalida = LocalDateTime.now();
        ticket.setHoraSalida(horaSalida);


        long minutosEstadia = Duration.between(ticket.getHoraEntrada(), ticket.getHoraSalida()).toMinutes();
        int precioPorMinuto = 50;
        int precioTotal = (int) minutosEstadia * precioPorMinuto;
        ticket.setPrecio(precioTotal);

        almacenarTickets.actualizarTicket(ticket);

        return "redirect:/";
    }

    @GetMapping("/eliminarTicket")
    public String eliminarTicket(@RequestParam("id") int id) {
        almacenarTickets.eliminarTicket(id);
        return "redirect:/";
    }
}
