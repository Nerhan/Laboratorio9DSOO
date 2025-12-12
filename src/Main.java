import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Usar SwingUtilities para asegurar que la GUI se ejecute en el hilo adecuado
        SwingUtilities.invokeLater(() -> {
            // Iniciar con la ventana de login
            VentanaLogin login = new VentanaLogin();
            login.setVisible(true);
        });
    }
}