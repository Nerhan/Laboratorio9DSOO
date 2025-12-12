public class ClienteVIP extends UsuarioCliente {
    private static final double LIMITE_RETIRO = 5000.0;

    public ClienteVIP(String id, String correo, String contraseña, String estado,
                      String nombre, String apellido, String direccion, String telefono) {
        super(id, correo, contraseña, estado, nombre, apellido, direccion, telefono);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos ClienteVIP: consultar cuentas, saldo, movimientos, depositar (propia), retirar (propia) | Límite retiro por operación: S/ " + LIMITE_RETIRO + " | Reportes detallados VIP");
    }

    public double getLimiteRetiro() { return LIMITE_RETIRO; }

    public void reporteVIP() {
        System.out.println("Generando reporte VIP de movimientos...");
        for (Cuenta c : cuentas) {
            c.verMovimientos();
        }
    }
}
