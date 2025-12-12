import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioRegistroCliente extends JFrame {
    private JTextField txtId, txtNombre, txtApellido, txtDireccion, txtTelefono, txtCorreo;
    private JPasswordField txtContraseña;
    private JComboBox<String> cmbTipo;
    private JButton btnRegistrar, btnCancelar;

    public FormularioRegistroCliente() {
        setTitle("Registrar Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panel.add(txtApellido);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        panel.add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        panel.add(txtContraseña);

        panel.add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Normal", "VIP"});
        panel.add(cmbTipo);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
        panel.add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void registrar() {
        try {
            String id = txtId.getText().trim().toUpperCase();
            ValidadorGUI.validarId(id);
            String nombre = txtNombre.getText().trim();
            ValidadorGUI.validarNombre(nombre, "Nombre");
            String apellido = txtApellido.getText().trim();
            ValidadorGUI.validarNombre(apellido, "Apellido");
            String direccion = txtDireccion.getText().trim();
            ValidadorGUI.validarNombre(direccion, "Dirección");
            String telefono = txtTelefono.getText().trim();
            ValidadorGUI.validarTelefono(telefono);
            String correo = txtCorreo.getText().trim();
            ValidadorGUI.validarCorreo(correo);
            String pass = new String(txtContraseña.getPassword());

            UsuarioCliente nuevo;
            if (cmbTipo.getSelectedItem().equals("VIP")) {
                nuevo = new ClienteVIP(id, correo, pass, "activo", nombre, apellido, direccion, telefono);
            } else {
                nuevo = new ClienteNormal(id, correo, pass, "activo", nombre, apellido, direccion, telefono);
            }

            AppContext.getInstance().getGestor().registrarUsuario(nuevo);
            JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}