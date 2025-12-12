import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioDeposito extends JFrame {
    private JTextField txtNumeroCuenta, txtMonto;
    private JButton btnDepositar, btnCancelar;

    public FormularioDeposito() {
        setTitle("Depósito");
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

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        panel.add(btnDepositar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void depositar() {
        try {
            String num = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(num);
            double monto = ValidadorGUI.validarDoublePositivo(txtMonto.getText().trim(), "Monto");
            Usuario usuario = AppContext.getInstance().getUsuarioActual();
            UsuarioEmpleado empleado = (usuario instanceof UsuarioEmpleado) ? (UsuarioEmpleado) usuario : null;
            AppContext.getInstance().getBanco().registrarDeposito(num, monto, empleado);
            JOptionPane.showMessageDialog(this, "Depósito realizado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}