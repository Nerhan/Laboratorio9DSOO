public class ValidadorGUI {

    public static void validarId(String id) {
        if (id.length() != 10) {
            throw new IllegalArgumentException("El ID debe tener exactamente 10 caracteres.");
        }
        if (id.contains(" ")) {
            throw new IllegalArgumentException("El ID no puede contener espacios.");
        }
        for (char c : id.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("El ID solo puede contener letras y números (sin símbolos).");
            }
        }
    }

    public static void validarDni(String dni) {
        if (dni.length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos.");
        }
        for (char c : dni.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El DNI solo debe contener números.");
            }
        }
    }

    public static void validarNombre(String texto, String campo) {
        if (texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        // Puedes agregar más validaciones de tu Validador original
    }

    public static void validarTelefono(String telefono) {
        if (telefono.length() != 9) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
        }
        for (char c : telefono.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El teléfono solo debe contener números.");
            }
        }
    }

    public static void validarCorreo(String correo) {
        if (correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        int arroba = correo.indexOf('@');
        int ultimoPunto = correo.lastIndexOf('.');
        if (arroba <= 0 || arroba != correo.lastIndexOf('@')) {
            throw new IllegalArgumentException("El correo debe tener exactamente un @.");
        }
        if (ultimoPunto <= arroba + 1 || ultimoPunto == correo.length() - 1) {
            throw new IllegalArgumentException("El correo debe tener al menos un punto después del @ (ej: nombre@dominio.com).");
        }
    }

    public static void validarNumeroCuenta(String num) {
        if (num.length() != 5) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 5 dígitos.");
        }
        if (!num.matches("\\d{5}")) {
            throw new IllegalArgumentException("El número de cuenta solo debe contener números.");
        }
    }

    public static double validarDoublePositivo(String montoStr, String campo) {
        try {
            double valor = Double.parseDouble(montoStr);
            if (valor <= 0) {
                throw new IllegalArgumentException(campo + " debe ser mayor a 0.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " debe ser un número válido.");
        }
    }

    // Agrega más si necesitas, como validarTipoCuenta, etc.
}