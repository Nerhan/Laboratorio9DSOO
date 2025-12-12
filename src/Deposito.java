public class Deposito extends Transaccion {
    public Deposito(Cuenta cuenta, double monto) {
        super(cuenta, monto);
    }

    @Override
    public void procesar() {
        getCuenta().depositar(getMonto());
        getCuenta().agregarTransaccion(this);
        System.out.println("Depósito exitoso de S/ " + getMonto() +
                " → Saldo actual: S/ " + getCuenta().getSaldo());
    }
}
