import java.util.Scanner;
public class Validador {
    public static int leerEntero(Scanner sc, String mensajeError) {
        while (!sc.hasNextInt()) {
            System.out.print(mensajeError);
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    public static double leerDoublePositivo(Scanner sc, String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            while (!sc.hasNextDouble()) {
                System.out.println(" Debe ingresar un número válido.");
                sc.next();
            }
            valor = sc.nextDouble();
            sc.nextLine();
            if (valor <= 0) {
                System.out.println(" El monto debe ser mayor a 0.");
            } else {
                return valor;
            }
        } while (true);
    }

    public static String leerId(Scanner sc, String mensaje) {
        String id;
        do {
            System.out.print(mensaje);
            id = sc.nextLine().trim().toUpperCase();
            if (id.length() != 10) {
                System.out.println("El ID debe tener exactamente 10 caracteres.");
                continue;
            }
            if (id.contains(" ")) {
                System.out.println("El ID no puede contener espacios.");
                continue;
            }
            boolean soloLetrasYNumeros = true;
            for (char c : id.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    soloLetrasYNumeros = false;
                    break;
                }
            }
            if (!soloLetrasYNumeros) {
                System.out.println("El ID solo puede contener letras y números (sin símbolos).");
            } else {
                return id;
            }
        } while (true);
    }

    public static String leerDni(Scanner sc, String mensaje) {
        String dni;
        do {
            System.out.print(mensaje);
            dni = sc.nextLine().trim();
            if (dni.isEmpty()) {
                System.out.println(" El DNI no puede estar vacío.");
                continue;
            }
            if (dni.length() != 8) {
                System.out.println(" El DNI debe tener exactamente 8 dígitos.");
                continue;
            }
            boolean soloNumeros = true;
            for (int i = 0; i < dni.length(); i++) {
                if (!Character.isDigit(dni.charAt(i))) {
                    soloNumeros = false;
                    break;
                }
            }
            if (!soloNumeros) {
                System.out.println(" El DNI solo debe contener números.");
            } else {
                return dni;
            }
        } while (true);
    }

    public static String leerNombre(Scanner sc, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
                continue;
            }
            boolean valido = true;
            for (char c : texto.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c != ' ' && c != ',' && c != '.' && c != '#' && c != '-') {
                    valido = false;
                    break;
                }
            }
            if (!valido) {
                System.out.println("Solo letras, números, espacios y símbolos como # , . - son permitidos (dirección realista).");
            } else {
                return texto;
            }
        } while (true);
    }

    public static String leerTelefono(Scanner sc, String mensaje) {
        String telefono;
        do {
            System.out.print(mensaje);
            telefono = sc.nextLine().trim();
            if (telefono.isEmpty()) {
                System.out.println(" El teléfono no puede estar vacío.");
                continue;
            }
            if (telefono.length() != 9) {
                System.out.println(" El teléfono debe tener exactamente 9 dígitos.");
                continue;
            }
            boolean soloNumeros = true;
            for (int i = 0; i < telefono.length(); i++) {
                if (!Character.isDigit(telefono.charAt(i))) {
                    soloNumeros = false;
                    break;
                }
            }
            if (!soloNumeros) {
                System.out.println(" El teléfono solo debe contener números.");
            } else {
                return telefono;
            }
        } while (true);
    }

    public static String leerCorreo(Scanner sc, String mensaje) {
        String correo;
        do {
            System.out.print(mensaje);
            correo = sc.nextLine().trim().toLowerCase();
            if (correo.isEmpty()) {
                System.out.println("El correo no puede estar vacío.");
                continue;
            }
            int arroba = correo.indexOf('@');
            int ultimoPunto = correo.lastIndexOf('.');

            if (arroba <= 0 || arroba != correo.lastIndexOf('@')) {
                System.out.println("El correo debe tener exactamente un @.");
            } else if (ultimoPunto <= arroba + 1 || ultimoPunto == correo.length() - 1) {
                System.out.println("El correo debe tener al menos un punto después del @ (ej: nombre@dominio.com).");
            } else {
                return correo;
            }
        } while (true);
    }

    public static String leerNumeroCuenta(Scanner sc, String mensaje) {
        String num;
        do {
            System.out.print(mensaje);
            num = sc.nextLine().trim();
            if (num.length() != 5) {
                System.out.println("El número de cuenta debe tener exactamente 5 dígitos.");
                continue;
            }
            if (!num.matches("\\d{5}")) {
                System.out.println("El número de cuenta solo debe contener números.");
            } else {
                return num;
            }
        } while (true);
    }

    public static String leerTipoCuenta(Scanner sc) {
        String tipo;
        do {
            System.out.print("Tipo de cuenta (Ahorros / Corriente): ");
            tipo = sc.nextLine().trim();
            if (!tipo.equalsIgnoreCase("Ahorros") && !tipo.equalsIgnoreCase("Corriente")) {
                System.out.println(" Solo se permite 'Ahorros' o 'Corriente'.");
            }
        } while (!tipo.equalsIgnoreCase("Ahorros") && !tipo.equalsIgnoreCase("Corriente"));
        return tipo;
    }

    public static String leerTipoTitular(Scanner sc) {
        String tipo;
        do {
            System.out.print("Tipo titular (Principal / Secundario): ");
            tipo = sc.nextLine().trim();
            if (!tipo.equalsIgnoreCase("Principal") && !tipo.equalsIgnoreCase("Secundario")) {
                System.out.println(" Solo se permite 'Principal' o 'Secundario'.");
            }
        } while (!tipo.equalsIgnoreCase("Principal") && !tipo.equalsIgnoreCase("Secundario"));
        return tipo;
    }
}
