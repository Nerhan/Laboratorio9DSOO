import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class FormularioRegistroCuenta extends JFrame {
    private JTextField txtNumeroCuenta, txtSaldoInicial;
    private JComboBox<String> cmbTipoCuenta;
    private JButton btnCrear, btnCancelar;

    public FormularioRegistroCuenta() {
        setTitle("Registrar Cuenta");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField();
        panel.add(txtNumeroCuenta);

        panel.add(new JLabel("Tipo de Cuenta:"));
        cmbTipoCuenta = new JComboBox<>(new String[]{"Ahorros", "Corriente"});
        panel.add(cmbTipoCuenta);

        panel.add(new JLabel("Saldo Inicial:"));
        txtSaldoInicial = new JTextField();
        panel.add(txtSaldoInicial);

        btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCuenta();
            }
        });
        panel.add(btnCrear);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void crearCuenta() {
        try {
            String num = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(num);
            String tipo = (String) cmbTipoCuenta.getSelectedItem();
            double saldo = ValidadorGUI.validarDoublePositivo(txtSaldoInicial.getText().trim(), "Saldo Inicial");

            Cuenta cuenta = new Cuenta(num, tipo, saldo, LocalDate.now());
            AppContext.getInstance().getBanco().crearCuenta(cuenta);
            JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}