public abstract class UsuarioEmpleado extends Usuario {

    public UsuarioEmpleado(String id, String correo, String contraseña, String estado,
                           String nombre, String apellido, String direccion, String telefono) {
        super(id, correo, contraseña, estado, nombre, apellido, direccion, telefono);
    }

    // procesar transaccion 
    public void procesarTransaccion(Transaccion t) {
        t.setEmpleado(this);
        t.procesar();
        System.out.println("Transacción procesada por " + getNombre() + " " + getApellido());
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos Empleado: procesar depósitos y retiros, consultar cuentas y movimientos, crear cuentas (según rol).");
    }
}
