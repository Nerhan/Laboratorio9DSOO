import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioConsultaSaldo extends JFrame {
    private JTextField txtNumeroCuenta;
    private JLabel lblSaldo;
    private JButton btnConsultar, btnCerrar;

    public FormularioConsultaSaldo() {
        setTitle("Consulta de Saldo");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField();
        panel.add(txtNumeroCuenta);

        panel.add(new JLabel("Saldo:"));
        lblSaldo = new JLabel("N/A");
        panel.add(lblSaldo);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
        panel.add(btnConsultar);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);

        add(panel);
    }

    private void consultar() {
        try {
            String num = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(num);
            Cuenta cuenta = AppContext.getInstance().getBanco().buscarCuentaPorNumero(num);
            lblSaldo.setText("S/ " + cuenta.getSaldo());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}