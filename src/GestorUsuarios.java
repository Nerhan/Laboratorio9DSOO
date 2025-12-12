import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GestorUsuarios {
    private Map<String, Usuario> usuariosPorCorreo = new HashMap<>();
    private Banco banco;

    public GestorUsuarios(Banco banco) {
        this.banco = banco;
    }


    public void registrarUsuario(Usuario u) {

        if (usuariosPorCorreo.containsKey(u.getCorreo().toLowerCase())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        usuariosPorCorreo.put(u.getCorreo().toLowerCase(), u);

        if (u instanceof UsuarioCliente cliente) {
            banco.registrarCliente(cliente);
        }
        else if (u instanceof UsuarioEmpleado empleado) {
            banco.registrarEmpleado(empleado);
        }
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuariosPorCorreo.get(correo.toLowerCase());
    }

    public Usuario login(String correo, String contraseña) {
        Usuario u = buscarPorCorreo(correo);
        if (u == null) return null;
        if (u.login(correo, contraseña)) return u;
        return null;
    }

    // utilidad para generar contraseña sencilla/única (puedes cambiar criterio)
    public static String generarContraseñaSegura() {
        return "P@" + UUID.randomUUID().toString().substring(0, 8);
    }

    public Banco getBanco() {
        return banco;
    }
}
