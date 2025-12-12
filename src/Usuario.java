public abstract class Usuario {
    protected String id;        
    protected String correo;     
    protected String contraseña;
    protected String estado;     
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected String telefono;

    public Usuario(String id, String correo, String contraseña,
                   String estado, String nombre, String apellido,
                   String direccion, String telefono) {
        this.id = id;
        this.correo = correo.toLowerCase();
        this.contraseña = contraseña;
        this.estado = estado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public boolean login(String correo, String contraseña) {
        return this.correo.equalsIgnoreCase(correo) && this.contraseña.equals(contraseña) && "activo".equalsIgnoreCase(estado);
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " " + apellido + " | Correo: " + correo + " | Estado: " + estado);
    }

    public abstract void mostrarPermisos();
}