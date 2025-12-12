import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private Usuario usuarioActual;
    private Banco banco;
    private GestorUsuarios gestor;

    public VentanaPrincipal() {
        AppContext context = AppContext.getInstance();
        usuarioActual = context.getUsuarioActual();
        banco = context.getBanco();
        gestor = context.getGestor();

        setTitle("Sistema Bancario - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        // Botones comunes
        JButton btnRegistrarCliente = new JButton("Registrar Cliente");
        btnRegistrarCliente.addActionListener(e -> new FormularioRegistroCliente().setVisible(true));
        panel.add(btnRegistrarCliente);

        JButton btnRegistrarCuenta = new JButton("Registrar Cuenta");
        btnRegistrarCuenta.addActionListener(e -> new FormularioRegistroCuenta().setVisible(true));
        panel.add(btnRegistrarCuenta);

        JButton btnDeposito = new JButton("Depósito");
        btnDeposito.addActionListener(e -> new FormularioDeposito().setVisible(true));
        panel.add(btnDeposito);

        JButton btnRetiro = new JButton("Retiro");
        btnRetiro.addActionListener(e -> new FormularioRetiro().setVisible(true));
        panel.add(btnRetiro);

        JButton btnConsultaSaldo = new JButton("Consulta Saldo");
        btnConsultaSaldo.addActionListener(e -> new FormularioConsultaSaldo().setVisible(true));
        panel.add(btnConsultaSaldo);

        JButton btnConsultaMovimientos = new JButton("Consulta Movimientos");
        btnConsultaMovimientos.addActionListener(e -> new FormularioConsultaMovimientos().setVisible(true));
        panel.add(btnConsultaMovimientos);

        JButton btnVincularCuenta = new JButton("Vincular Cuenta a Cliente");
        btnVincularCuenta.addActionListener(e -> new FormularioVincularCuenta().setVisible(true));
        panel.add(btnVincularCuenta);

        // Botones según rol
        if (usuarioActual instanceof Administrador || usuarioActual instanceof Gerente) {
            JButton btnRegistrarEmpleado = new JButton("Registrar Empleado");
            btnRegistrarEmpleado.addActionListener(e -> new FormularioRegistroEmpleado().setVisible(true));
            panel.add(btnRegistrarEmpleado);
        }

        if (usuarioActual instanceof Administrador) {
            JButton btnEliminarCliente = new JButton("Eliminar Cliente");
            btnEliminarCliente.addActionListener(e -> eliminar("cliente"));
            panel.add(btnEliminarCliente);

            JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
            btnEliminarEmpleado.addActionListener(e -> eliminar("empleado"));
            panel.add(btnEliminarEmpleado);

            JButton btnEliminarCuenta = new JButton("Eliminar Cuenta");
            btnEliminarCuenta.addActionListener(e -> eliminar("cuenta"));
            panel.add(btnEliminarCuenta);
        }

        // Botones de listados
        JButton btnListar = new JButton("Listados");
        btnListar.addActionListener(e -> new FormularioListados().setVisible(true));
        panel.add(btnListar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {
            AppContext.getInstance().setUsuarioActual(null);
            new VentanaLogin().setVisible(true);
            dispose();
        });
        panel.add(btnSalir);

        // Deshabilitar botones según permisos (más fino si necesitas)
        if (usuarioActual instanceof UsuarioCliente) {
            btnRegistrarCliente.setEnabled(false);
            btnRegistrarCuenta.setEnabled(false);
            // etc., basado en mostrarPermisos()
        }

        add(new JScrollPane(panel));  // Por si hay muchos botones
    }

    private void eliminar(String tipo) {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID/" + tipo + " a eliminar:");
        try {
            if (tipo.equals("cliente")) {
                banco.eliminarCliente(id);
            } else if (tipo.equals("empleado")) {
                banco.eliminarEmpleado(id);
            } else if (tipo.equals("cuenta")) {
                banco.eliminarCuenta(id);
            }
            JOptionPane.showMessageDialog(this, tipo + " eliminado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}