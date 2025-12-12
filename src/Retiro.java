public class Retiro extends Transaccion {
    public Retiro(Cuenta cuenta, double monto) {
        super(cuenta, monto);
    }

    @Override
    public void procesar() {
        if (getMonto() <= 0) throw new IllegalArgumentException("El monto debe ser mayor a 0");

        if (getMonto() > getCuenta().getSaldo()) {
            throw new RuntimeException("✗ Fondos insuficientes. Saldo actual: S/ " + getCuenta().getSaldo() +
                    " | Intentó retirar: S/ " + getMonto());
        }
        getCuenta().retirar(getMonto());
        getCuenta().agregarTransaccion(this);
        System.out.println("✓ Retiro exitoso de S/ " + getMonto() +
                " → Saldo restante: S/ " + getCuenta().getSaldo());
    }
}
