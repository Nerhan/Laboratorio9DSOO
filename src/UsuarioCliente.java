import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioCliente extends Usuario {
    protected List<Cuenta> cuentas = new ArrayList<>();

    public UsuarioCliente(String id, String correo, String contraseña, String estado,
                          String nombre, String apellido, String direccion, String telefono) {
        super(id, correo, contraseña, estado, nombre, apellido, direccion, telefono);
    }

    public List<Cuenta> getCuentas() { return cuentas; }
    public void agregarCuenta(Cuenta c) {
        if (!cuentas.contains(c)) cuentas.add(c);
    }

    public void mostrarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No tiene cuentas asociadas.");
            return;
        }
        System.out.println("Cuentas de " + nombre + ":");
        for (Cuenta c : cuentas) System.out.println(" - " + c);
    }

    public void consultarSaldo(Cuenta c) {
        System.out.println("Saldo cuenta " + c.getNumeroCuenta() + ": S/ " + c.getSaldo());
    }

    public void verMovimientos(Cuenta c) {
        c.verMovimientos();
    }

    public void depositarEnPropiaCuenta(Cuenta c, double monto, Banco banco) {
        if (!cuentas.contains(c)) {
            System.out.println("No puede depositar en una cuenta que no es suya.");
            return;
        }
        banco.registrarDeposito(c.getNumeroCuenta(), monto, null); 
    }

    public void retirarDePropiaCuenta(Cuenta c, double monto, Banco banco) {
        if (!cuentas.contains(c)) {
            System.out.println("No puede retirar de una cuenta que no es suya.");
            return;
        }
        banco.registrarRetiro(c.getNumeroCuenta(), monto, null);
    }
}
