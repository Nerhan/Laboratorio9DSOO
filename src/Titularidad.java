import java.time.LocalDate;

public class Titularidad {
    private UsuarioCliente cliente;
    private Cuenta cuenta;
    private LocalDate fechaInicio;
    private String tipoTitular;

    public Titularidad(UsuarioCliente cliente, Cuenta cuenta, LocalDate fechaInicio, String tipoTitular) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.fechaInicio = fechaInicio;
        this.tipoTitular = tipoTitular;
    }

    public UsuarioCliente getCliente() { return cliente; }
    public Cuenta getCuenta() { return cuenta; }

    @Override
    public String toString() {
        return "Titularidad{" +
                "cliente=" + cliente.getNombre() + " " + cliente.getApellido() +
                ", cuenta=" + cuenta.getNumeroCuenta() +
                ", fechaInicio=" + fechaInicio +
                ", tipoTitular='" + tipoTitular + '\'' +
                '}';
    }
}
