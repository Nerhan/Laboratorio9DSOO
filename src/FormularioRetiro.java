import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioRetiro extends JFrame {
    private JTextField txtNumeroCuenta, txtMonto;
    private JButton btnRetirar, btnCancelar;

    public FormularioRetiro() {
        setTitle("Retiro");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField();
        panel.add(txtNumeroCuenta);

        panel.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panel.add(txtMonto);

        btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirar();
            }
        });
        panel.add(btnRetirar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void retirar() {
        try {
            String num = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(num);
            double monto = ValidadorGUI.validarDoublePositivo(txtMonto.getText().trim(), "Monto");
            Usuario usuario = AppContext.getInstance().getUsuarioActual();
            UsuarioEmpleado empleado = (usuario instanceof UsuarioEmpleado) ? (UsuarioEmpleado) usuario : null;
            AppContext.getInstance().getBanco().registrarRetiro(num, monto, empleado);
            JOptionPane.showMessageDialog(this, "Retiro realizado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}