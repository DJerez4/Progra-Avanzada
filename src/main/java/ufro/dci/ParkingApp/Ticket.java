package ufro.dci.ParkingApp;
import java.time.LocalDateTime;

public class Ticket {
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private int id;
    private int precio;

    // Constructor
    public Ticket(LocalDateTime horaEntrada, int id) {
        this.horaEntrada = horaEntrada;
        this.id = id;
    }

    // Getters y setters
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}