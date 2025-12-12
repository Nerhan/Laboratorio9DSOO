import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class FormularioListados extends JFrame {
    private JTabbedPane tabbedPane;
    private Banco banco;

    public FormularioListados() {
        setTitle("Listados");
        setSize(600, 400);
        setLocationRelativeTo(null);

        banco = AppContext.getInstance().getBanco();

        tabbedPane = new JTabbedPane();

        // Tab Clientes
        JTable tablaClientes = new JTable();
        cargarClientes(tablaClientes);
        tabbedPane.addTab("Clientes", new JScrollPane(tablaClientes));

        // Tab Empleados
        JTable tablaEmpleados = new JTable();
        cargarEmpleados(tablaEmpleados);
        tabbedPane.addTab("Empleados", new JScrollPane(tablaEmpleados));

        // Tab Cuentas
        JTable tablaCuentas = new JTable();
        cargarCuentas(tablaCuentas);
        tabbedPane.addTab("Cuentas", new JScrollPane(tablaCuentas));

        add(tabbedPane);
    }

    private void cargarClientes(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Correo"}, 0);
        for (UsuarioCliente c : banco.getClientes().values()) {
            model.addRow(new Object[]{c.getId(), c.getNombre(), c.getApellido(), c.getCorreo()});
        }
        tabla.setModel(model);
    }

    private void cargarEmpleados(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Correo", "Tipo"}, 0);
        for (UsuarioEmpleado e : banco.getEmpleados().values()) {
            model.addRow(new Object[]{e.getId(), e.getNombre(), e.getApellido(), e.getCorreo(), e.getClass().getSimpleName()});
        }
        tabla.setModel(model);
    }

    private void cargarCuentas(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Número", "Tipo", "Saldo", "Fecha Apertura"}, 0);
        for (Cuenta c : banco.getCuentas().values()) {
            model.addRow(new Object[]{c.getNumeroCuenta(), c.getTipoCuenta(), c.getSaldo(), c.getFechaApertura()});
        }
        tabla.setModel(model);
    }
}