import java.time.LocalDate;
import java.util.*;

public class Banco {
    public Map<String, UsuarioCliente> clientes = new HashMap<>(); 
    public Map<String, UsuarioEmpleado> empleados = new HashMap<>(); 
    public Map<String, Cuenta> cuentas = new HashMap<>(); 
    private List<Titularidad> titularidades = new ArrayList<>();

    public void registrarCliente(UsuarioCliente c) {
        if (clientes.containsKey(c.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese ID");
        }
        clientes.put(c.getId(), c);
        System.out.println("Cliente registrado: " + c.getNombre() + " " + c.getApellido());
    }

    public void registrarEmpleado(UsuarioEmpleado e) {
        if (empleados.containsKey(e.getId())) {
            throw new IllegalArgumentException("Ya existe un empleado con ese ID");
        }
        empleados.put(e.getId(), e);
        System.out.println("Empleado registrado: " + e.getNombre() + " " + e.getApellido() + " (" + e.getClass().getSimpleName() + ")");
    }

    public void crearCuenta(Cuenta c) {
        if (cuentas.containsKey(c.getNumeroCuenta())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese número");
        }
        cuentas.put(c.getNumeroCuenta(), c);
        System.out.println("Cuenta creada: " + c.getNumeroCuenta());
    }

    public UsuarioCliente buscarClientePorId(String id) {
        UsuarioCliente c = clientes.get(id);
        if (c == null) {
            throw new IllegalArgumentException("No existe cliente con ID: " + id);
        }
        return c;
    }

    public UsuarioEmpleado buscarEmpleadoPorId(String id) {
        return empleados.get(id);
    }

    public Cuenta buscarCuentaPorNumero(String numero) {
        Cuenta c = cuentas.get(numero);
        if (c == null) {
            throw new IllegalArgumentException("No existe cuenta número: " + numero);
        }
        return c;
    }

    public void asignarTitular(UsuarioCliente cliente, Cuenta cuenta, LocalDate fechaInicio, String tipoTitular) {
        for (Titularidad t : titularidades) {
            if (t.getCliente().equals(cliente) && t.getCuenta().equals(cuenta)) {
                throw new IllegalArgumentException("Este cliente ya es titular de esta cuenta");
            }
        }
        Titularidad t = new Titularidad(cliente, cuenta, fechaInicio, tipoTitular);
        titularidades.add(t);
        cliente.agregarCuenta(cuenta);
        System.out.println("Titularidad asignada: " + cliente.getNombre() + "  Cuenta " + cuenta.getNumeroCuenta());
    }

    public void registrarDeposito(String numeroCuenta, double monto, UsuarioEmpleado empleado) {
        Cuenta c = cuentas.get(numeroCuenta);
        if (c == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }
        Deposito d = new Deposito(c, monto);
        if (empleado != null) {
            empleado.procesarTransaccion(d);
        } else {
            d.procesar();
        }
    }

    public void registrarRetiro(String numeroCuenta, double monto, UsuarioEmpleado empleado) {
        Cuenta c = cuentas.get(numeroCuenta);
        if (c == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }
        Retiro r = new Retiro(c, monto);
        try {
            if (empleado != null) {
                empleado.procesarTransaccion(r);
            } else {
                r.procesar();
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void consultarSaldo(String numeroCuenta) {
        Cuenta c = cuentas.get(numeroCuenta);
        if (c == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }
        System.out.println("Saldo actual de la cuenta " + numeroCuenta + ": S/ " + c.getSaldo());
    }

    public void verMovimientos(String numeroCuenta) {
        Cuenta c = cuentas.get(numeroCuenta);
        if (c == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }
        c.verMovimientos();
    }

    public void listarCuentasCliente(String idCliente) {
        UsuarioCliente cl = clientes.get(idCliente);
        if (cl == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        List<Cuenta> cuentasCliente = cl.getCuentas();
        if (cuentasCliente.isEmpty()) {
            System.out.println("El cliente no tiene cuentas registradas.");
        } else {
            System.out.println("Cuentas del cliente " + cl.getNombre() + ":");
            for (Cuenta c : cuentasCliente) {
                System.out.println(" - " + c);
            }
        }
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }
        System.out.println("Lista de clientes:");
        for (UsuarioCliente c : clientes.values()) {
            System.out.println(" - " + c.getId() + ": " + c.getNombre() + " " + c.getApellido());
        }
    }

    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
            return;
        }
        System.out.println("Lista de empleados:");
        for (UsuarioEmpleado e : empleados.values()) {
            System.out.println(" - " + e.getId() + ": " + e.getNombre() + " " + e.getApellido() + " (" + e.getClass().getSimpleName() + ")");
        }
    }

    public void listarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas creadas.");
            return;
        }
        System.out.println("Lista de cuentas:");
        for (Cuenta c : cuentas.values()) {
            System.out.println(" - " + c);
        }
    }

    public void eliminarCliente(String idCliente) {
        if (clientes.remove(idCliente) != null) {
            System.out.println("Cliente eliminado: " + idCliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void eliminarEmpleado(String idEmpleado) {
        if (empleados.remove(idEmpleado) != null) {
            System.out.println("Empleado eliminado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    public void eliminarCuenta(String numeroCuenta) {
        if (cuentas.remove(numeroCuenta) != null) {
            System.out.println("Cuenta eliminada: " + numeroCuenta);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
    
    public Map<String, UsuarioCliente> getClientes() { return clientes; }
    public Map<String, UsuarioEmpleado> getEmpleados() { return empleados; }
    public Map<String, Cuenta> getCuentas() { return cuentas; }
}
