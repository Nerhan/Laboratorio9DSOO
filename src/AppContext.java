import java.time.LocalDate;

public class AppContext {
    private static AppContext instance;
    private Banco banco;
    private GestorUsuarios gestor;
    private Usuario usuarioActual;

    private AppContext() {
        banco = new Banco();
        gestor = new GestorUsuarios(banco);
        // Inicializa datos de prueba como en Main.java
        inicializarDatosPrueba();
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    private void inicializarDatosPrueba() {
        // Agrega usuarios de prueba como en tu Main.java
        Usuario admin = new Administrador(
                "ADM0000001",
                "admin@banco.com",
                "admin123",
                "activo",
                "Admin",
                "Principal",
                "Oficina Central",
                "999999999"
        );
        gestor.registrarUsuario(admin);

        UsuarioEmpleado gerente = new Gerente(
                "EMP0000001",
                "gerente@banco.com",
                "gerente123",
                "activo",
                "María",
                "García",
                "Sucursal Norte",
                "998887766"
        );
        gestor.registrarUsuario(gerente);

        UsuarioEmpleado cajero = new Cajero(
                "EMP0000002",
                "cajero@banco.com",
                "cajero123",
                "activo",
                "Carlos",
                "López",
                "Sucursal Sur",
                "997776655"
        );
        gestor.registrarUsuario(cajero);

        UsuarioCliente clienteNormal = new ClienteNormal(
                "CLI0000001",
                "cliente@banco.com",
                "cliente123",
                "activo",
                "Juan",
                "Pérez",
                "Av. Principal 123",
                "999111222"
        );
        gestor.registrarUsuario(clienteNormal);

        UsuarioCliente clienteVIP = new ClienteVIP(
                "CLI0000002",
                "vip@banco.com",
                "vip123",
                "activo",
                "Ana",
                "Vega",
                "Calle Falsa 456",
                "999333444"
        );
        gestor.registrarUsuario(clienteVIP);
    }

    public Banco getBanco() {
        return banco;
    }

    public GestorUsuarios getGestor() {
        return gestor;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}