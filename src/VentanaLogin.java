import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContraseña;
    private JButton btnLogin;
    private JButton btnSalir;

    public VentanaLogin() {
        setTitle("Sistema Bancario - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configuración de Look & Feel para aspecto más moderno
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel principal con márgenes y diseño mejorado
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel superior para el título
        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel titulo = new JLabel("Acceso al Sistema");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 70, 130)); // Azul corporativo
        tituloPanel.add(titulo);
        
        // Panel central para formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Etiqueta y campo para correo
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCorreo, gbc);
        
        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCorreo.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(txtCorreo, gbc);
        
        // Etiqueta y campo para contraseña
        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(lblContraseña, gbc);
        
        txtContraseña = new JPasswordField();
        txtContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContraseña.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(txtContraseña, gbc);
        
        // Panel para botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setBackground(new Color(0, 120, 215)); // Azul moderno
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 100, 190), 1),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnSalir.setBackground(new Color(220, 220, 220));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        botonesPanel.add(btnLogin);
        botonesPanel.add(btnSalir);
        
        
        // Ensamblar componentes
        panel.add(tituloPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(botonesPanel, BorderLayout.SOUTH);
        
        // Agregar un borde decorativo
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 240), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Configurar colores del contenedor principal
        panel.setBackground(Color.WHITE);
        formPanel.setBackground(Color.WHITE);
        tituloPanel.setBackground(Color.WHITE);
        botonesPanel.setBackground(Color.WHITE);
        
        add(panel);
        
        // Configurar ícono de la ventana (opcional)
        try {
            ImageIcon icon = new ImageIcon("icono.png"); // Ajusta la ruta si tienes un ícono
            setIconImage(icon.getImage());
        } catch (Exception e) {
            // Si no hay ícono, continuar sin él
        }
    }

    private void login() {
        String correo = txtCorreo.getText().trim();
        String pass = new String(txtContraseña.getPassword());
        try {
            ValidadorGUI.validarCorreo(correo);
            GestorUsuarios gestor = AppContext.getInstance().getGestor();
            Usuario usuario = gestor.login(correo, pass);
            if (usuario != null) {
                AppContext.getInstance().setUsuarioActual(usuario);
                JOptionPane.showMessageDialog(this, "Bienvenido: " + usuario.getNombre() + " " + usuario.getApellido());
                new VentanaPrincipal().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login fallido. Verifique credenciales o estado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
}