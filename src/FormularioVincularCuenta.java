import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class FormularioVincularCuenta extends JFrame {
    private JTextField txtIdCliente, txtNumeroCuenta;
    private JComboBox<String> cmbTipoTitular;
    private JButton btnVincular, btnCancelar;

    public FormularioVincularCuenta() {
        setTitle("Vincular Cuenta a Cliente");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("ID Cliente:"));
        txtIdCliente = new JTextField();
        panel.add(txtIdCliente);

        panel.add(new JLabel("Número de Cuenta:"));
        txtNumeroCuenta = new JTextField();
        panel.add(txtNumeroCuenta);

        panel.add(new JLabel("Tipo Titular:"));
        cmbTipoTitular = new JComboBox<>(new String[]{"Principal", "Secundario"});
        panel.add(cmbTipoTitular);

        btnVincular = new JButton("Vincular");
        btnVincular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vincular();
            }
        });
        panel.add(btnVincular);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void vincular() {
        try {
            String idCliente = txtIdCliente.getText().trim().toUpperCase();
            ValidadorGUI.validarId(idCliente);
            String numCuenta = txtNumeroCuenta.getText().trim();
            ValidadorGUI.validarNumeroCuenta(numCuenta);
            String tipoTitular = (String) cmbTipoTitular.getSelectedItem();

            Banco banco = AppContext.getInstance().getBanco();
            UsuarioCliente cliente = banco.buscarClientePorId(idCliente);
            Cuenta cuenta = banco.buscarCuentaPorNumero(numCuenta);
            banco.asignarTitular(cliente, cuenta, LocalDate.now(), tipoTitular);
            JOptionPane.showMessageDialog(this, "Cuenta vinculada exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}