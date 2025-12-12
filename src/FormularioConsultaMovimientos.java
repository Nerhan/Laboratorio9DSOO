import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioConsultaMovimientos extends JFrame {
    private JTextField txtNumeroCuenta;
    private JTable tablaMovimientos;
    private JButton btnVer;
    private JButton btnCerrar;

    public FormularioConsultaMovimientos() {
        setTitle("Consulta de Movimientos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        JPanel panelNorte = new JPanel();
        panelNorte.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField(10);
        panelNorte.add(txtNumeroCuenta);
        btnVer = new JButton("Ver Movimientos");
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verMovimientos();
            }
        });
        panelNorte.add(btnVer);
        tablaMovimientos = new JTable();
        add(new JScrollPane(tablaMovimientos), BorderLayout.CENTER);
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);
    }

    private void verMovimientos() {
        try {
            String num = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(num);
            Cuenta cuenta = AppContext.getInstance().getBanco().buscarCuentaPorNumero(num);

            DefaultTableModel model = new DefaultTableModel(new String[]{"Fecha", "Tipo", "Monto", "Empleado"}, 0);
            for (Transaccion t : cuenta.transacciones) {  // Acceso directo ya que es pública
                String emp = (t.getEmpleado() == null) ? "N/A" : t.getEmpleado().getNombre() + " " + t.getEmpleado().getApellido();
                model.addRow(new Object[]{t.getFecha(), t.getClass().getSimpleName(), t.getMonto(), emp});
            }
            tablaMovimientos.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}