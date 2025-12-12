public class ClienteNormal extends UsuarioCliente {
    private static final double LIMITE_RETIRO = 1500.0;

    public ClienteNormal(String id, String correo, String contraseña, String estado,
                         String nombre, String apellido, String direccion, String telefono) {
        super(id, correo, contraseña, estado, nombre, apellido, direccion, telefono);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos ClienteNormal: consultar cuentas, saldo, movimientos, depositar (propia), retirar (propia) | Límite retiro por operación: S/ " + LIMITE_RETIRO);
    }

    public double getLimiteRetiro() { return LIMITE_RETIRO; }
}
