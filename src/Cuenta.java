import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    public List<Transaccion> transacciones = new ArrayList<>();

    public Cuenta(String numeroCuenta, String tipoCuenta, double saldoInicial, LocalDate fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldoInicial;
        this.fechaApertura = fechaApertura;
    }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTipoCuenta() { return tipoCuenta; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public void depositar(double monto) {
        if (monto > 0) saldo += monto;
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) saldo -= monto;
    }

    public void agregarTransaccion(Transaccion t) {
        transacciones.add(t);
    }

    public void verMovimientos() {
        if (transacciones.isEmpty()) {
            System.out.println("No hay movimientos en la cuenta " + numeroCuenta);
        } else {
            System.out.println("Movimientos de la cuenta " + numeroCuenta + ":");
            for (Transaccion t : transacciones) {
                System.out.println(t);
            }
        }
    }
    
    public LocalDate getFechaApertura() { return fechaApertura; }

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta='" + numeroCuenta + '\'' + ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo + ", fechaApertura=" + fechaApertura + '}';
    }
}
