
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidadorGUI {

    // Validación de ID (10 caracteres alfanuméricos)
    public static void validarId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío.");
        }
        id = id.trim().toUpperCase();
        
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
        
        // Validación adicional: primeros 3 caracteres indican tipo
        String prefijo = id.substring(0, 3);
        if (!prefijo.equals("ADM") && !prefijo.equals("EMP") && !prefijo.equals("CLI")) {
            throw new IllegalArgumentException("ID inválido. Debe comenzar con ADM, EMP o CLI.");
        }
    }

    // Validación de DNI (8 dígitos)
    public static void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        dni = dni.trim();
        
        if (dni.length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos.");
        }
        for (char c : dni.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El DNI solo debe contener números.");
            }
        }
    }

    // Validación de nombres y apellidos
    public static void validarNombre(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        texto = texto.trim();
        
        if (texto.length() < 2) {
            throw new IllegalArgumentException(campo + " debe tener al menos 2 caracteres.");
        }
        if (texto.length() > 50) {
            throw new IllegalArgumentException(campo + " no puede exceder los 50 caracteres.");
        }
        
        // Solo letras, espacios y algunos caracteres especiales permitidos
        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ' && c != '-' && c != '\'') {
                throw new IllegalArgumentException(campo + " solo puede contener letras, espacios, guiones y apóstrofes.");
            }
        }
    }

    // Validación de dirección
    public static void validarDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        direccion = direccion.trim();
        
        if (direccion.length() < 5) {
            throw new IllegalArgumentException("La dirección debe tener al menos 5 caracteres.");
        }
        if (direccion.length() > 100) {
            throw new IllegalArgumentException("La dirección no puede exceder los 100 caracteres.");
        }
        
        // Permite letras, números, espacios y caracteres comunes en direcciones
        for (char c : direccion.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != ' ' && c != ',' && c != '.' && c != '#' && 
                c != '-' && c != 'º' && c != 'ª' && c != '/') {
                throw new IllegalArgumentException("La dirección contiene caracteres no permitidos.");
            }
        }
    }

    // Validación de teléfono (9 dígitos)
    public static void validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        telefono = telefono.trim();
        
        if (telefono.length() != 9) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
        }
        if (!telefono.startsWith("9")) {
            throw new IllegalArgumentException("El teléfono debe comenzar con 9 (formato peruano).");
        }
        for (char c : telefono.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El teléfono solo debe contener números.");
            }
        }
    }

    // Validación de correo electrónico robusta
    public static void validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        correo = correo.trim().toLowerCase();
        
        // Expresión regular mejorada para validación de correo
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        
        if (!correo.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de correo electrónico inválido. Ejemplo: usuario@dominio.com");
        }
        
        // Validación adicional de longitud
        if (correo.length() > 100) {
            throw new IllegalArgumentException("El correo no puede exceder los 100 caracteres.");
        }
        
        // Validar que el dominio tenga al menos un punto después del @
        int arrobaIndex = correo.indexOf('@');
        String dominio = correo.substring(arrobaIndex + 1);
        if (!dominio.contains(".")) {
            throw new IllegalArgumentException("El dominio del correo debe contener al menos un punto.");
        }
    }

    // Validación de número de cuenta (5 dígitos)
    public static void validarNumeroCuenta(String num) {
        if (num == null || num.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío.");
        }
        num = num.trim();
        
        if (num.length() != 5) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 5 dígitos.");
        }
        if (!num.matches("\\d{5}")) {
            throw new IllegalArgumentException("El número de cuenta solo debe contener números.");
        }
        
        // Validar que no sea una secuencia simple (11111, 12345, etc.)
        if (num.matches("(\\d)\\1{4}")) { // Todos dígitos iguales
            throw new IllegalArgumentException("Número de cuenta no válido (dígitos idénticos).");
        }
    }

    // Validación de monto (double positivo)
    public static double validarDoublePositivo(String montoStr, String campo) {
        if (montoStr == null || montoStr.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        
        try {
            double valor = Double.parseDouble(montoStr.trim());
            
            if (valor <= 0) {
                throw new IllegalArgumentException(campo + " debe ser mayor a 0.");
            }
            
            if (valor > 1_000_000) { // Límite máximo de transacción
                throw new IllegalArgumentException(campo + " no puede exceder S/ 1,000,000.00");
            }
            
            // Validar decimales (máximo 2)
            String[] partes = montoStr.trim().split("\\.");
            if (partes.length > 1 && partes[1].length() > 2) {
                throw new IllegalArgumentException(campo + " no puede tener más de 2 decimales.");
            }
            
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " debe ser un número válido (ej: 100.50).");
        }
    }

    // Validación de contraseña
    public static void validarContraseña(String contraseña) {
        if (contraseña == null || contraseña.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        contraseña = contraseña.trim();
        
        if (contraseña.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        if (contraseña.length() > 20) {
            throw new IllegalArgumentException("La contraseña no puede exceder los 20 caracteres.");
        }
        
        // Validar seguridad básica
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        
        for (char c : contraseña.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isLowerCase(c)) tieneMinuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }
        
        if (!tieneMayuscula || !tieneMinuscula || !tieneNumero) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula, una minúscula y un número.");
        }
    }

    // Validación de estado
    public static void validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío.");
        }
        estado = estado.trim().toLowerCase();
        
        if (!estado.equals("activo") && !estado.equals("inactivo") && 
            !estado.equals("suspendido") && !estado.equals("bloqueado")) {
            throw new IllegalArgumentException("Estado inválido. Valores permitidos: activo, inactivo, suspendido, bloqueado.");
        }
    }

    // Validación de tipo de cuenta
    public static void validarTipoCuenta(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cuenta no puede estar vacío.");
        }
        tipo = tipo.trim();
        
        if (!tipo.equalsIgnoreCase("Ahorros") && !tipo.equalsIgnoreCase("Corriente")) {
            throw new IllegalArgumentException("Tipo de cuenta inválido. Valores permitidos: Ahorros, Corriente.");
        }
    }

    // Validación de tipo de titular
    public static void validarTipoTitular(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de titular no puede estar vacío.");
        }
        tipo = tipo.trim();
        
        if (!tipo.equalsIgnoreCase("Principal") && !tipo.equalsIgnoreCase("Secundario")) {
            throw new IllegalArgumentException("Tipo de titular inválido. Valores permitidos: Principal, Secundario.");
        }
    }

    // Validación de fecha (formato YYYY-MM-DD)
    public static LocalDate validarFecha(String fechaStr, String campo) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        
        try {
            LocalDate fecha = LocalDate.parse(fechaStr.trim());
            
            // Validar que no sea fecha futura (para apertura de cuenta)
            if (fecha.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(campo + " no puede ser una fecha futura.");
            }
            
            // Validar que no sea muy antigua (más de 100 años)
            if (fecha.isBefore(LocalDate.now().minusYears(100))) {
                throw new IllegalArgumentException(campo + " no puede ser anterior a hace 100 años.");
            }
            
            return fecha;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido para " + campo + ". Use formato YYYY-MM-DD.");
        }
    }

    // Validación de rango numérico
    public static int validarEnteroEnRango(String valorStr, String campo, int min, int max) {
        if (valorStr == null || valorStr.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        
        try {
            int valor = Integer.parseInt(valorStr.trim());
            
            if (valor < min) {
                throw new IllegalArgumentException(campo + " debe ser mayor o igual a " + min + ".");
            }
            
            if (valor > max) {
                throw new IllegalArgumentException(campo + " debe ser menor o igual a " + max + ".");
            }
            
            return valor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " debe ser un número entero válido.");
        }
    }

    // Validación de tipo de empleado
    public static void validarTipoEmpleado(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de empleado no puede estar vacío.");
        }
        tipo = tipo.trim();
        
        if (!tipo.equalsIgnoreCase("Cajero") && !tipo.equalsIgnoreCase("Gerente") && 
            !tipo.equalsIgnoreCase("Administrador")) {
            throw new IllegalArgumentException("Tipo de empleado inválido. Valores permitidos: Cajero, Gerente, Administrador.");
        }
    }

    // Validación de tipo de transacción
    public static void validarTipoTransaccion(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de transacción no puede estar vacío.");
        }
        tipo = tipo.trim();
        
        if (!tipo.equalsIgnoreCase("Deposito") && !tipo.equalsIgnoreCase("Retiro") && 
            !tipo.equalsIgnoreCase("Transferencia")) {
            throw new IllegalArgumentException("Tipo de transacción inválido. Valores permitidos: Deposito, Retiro, Transferencia.");
        }
    }

    // Validación para asegurar que un campo no sea nulo
    public static void validarNoNulo(Object objeto, String campo) {
        if (objeto == null) {
            throw new IllegalArgumentException(campo + " no puede ser nulo.");
        }
    }

    // Validación de longitud de texto
    public static void validarLongitud(String texto, String campo, int min, int max) {
        if (texto == null) {
            throw new IllegalArgumentException(campo + " no puede ser nulo.");
        }
        
        int longitud = texto.trim().length();
        
        if (longitud < min) {
            throw new IllegalArgumentException(campo + " debe tener al menos " + min + " caracteres.");
        }
        
        if (longitud > max) {
            throw new IllegalArgumentException(campo + " no puede exceder los " + max + " caracteres.");
        }
    }

    // Validación de confirmación de contraseña
    public static void validarConfirmacionContraseña(String contraseña, String confirmacion) {
        validarContraseña(contraseña);
        validarContraseña(confirmacion);
        
        if (!contraseña.equals(confirmacion)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
    }

    // Validación de saldo mínimo para apertura de cuenta
    public static void validarSaldoMinimo(double saldo, String tipoCuenta) {
        double saldoMinimo = 0.0;
        
        if (tipoCuenta.equalsIgnoreCase("Ahorros")) {
            saldoMinimo = 20.0; // Saldo mínimo para cuenta de ahorros
        } else if (tipoCuenta.equalsIgnoreCase("Corriente")) {
            saldoMinimo = 100.0; // Saldo mínimo para cuenta corriente
        }
        
        if (saldo < saldoMinimo) {
            throw new IllegalArgumentException("El saldo inicial para cuenta " + tipoCuenta + 
                                               " debe ser al menos S/ " + saldoMinimo);
        }
    }

    // Validación de límite de retiro según tipo de cliente
    public static void validarLimiteRetiro(double monto, String tipoCliente) {
        double limite = 0.0;
        
        if (tipoCliente.equals("ClienteNormal")) {
            limite = 1500.0;
        } else if (tipoCliente.equals("ClienteVIP")) {
            limite = 5000.0;
        }
        
        if (monto > limite) {
            throw new IllegalArgumentException("El monto excede el límite de retiro para cliente " + 
                                               tipoCliente + " (Límite: S/ " + limite + ")");
        }
    }
}
