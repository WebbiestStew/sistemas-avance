import java.rmi.registry.LocateRegistry; // Permite buscar el registro en un servidor RMI
import java.rmi.registry.Registry; // Permite acceder a un registro en un servidor RMI
import java.util.Scanner; // Se usa para leer la entrada del usuario desde la consola

public class ClienteRMI {
    private static final String SERVIDOR = "127.0.0.1"; // Dirección del servidor RMI
    private static final int PUERTO_RMI = 1099; // Puerto del registro RMI

    public static void main(String[] args) {
        try {
            // Conectar al registro RMI en el servidor
            Registry registro = LocateRegistry.getRegistry(SERVIDOR, PUERTO_RMI);
            Middleware middleware = (Middleware) registro.lookup("Middleware"); // Buscar Middleware

            Scanner scanner = new Scanner(System.in);
            System.out.println("Conectado al Middleware RMI.");
            mostrarComandos();

            while (true) {
                System.out.print("> ");
                String comando = scanner.nextLine();

                if ("salir".equalsIgnoreCase(comando)) { // Si el usuario escribe "salir", termina la conexión
                    System.out.println("Desconectando...");
                    break;
                }

                // Enviar el comando al middleware y recibir la respuesta
                String respuesta = middleware.ejecutarComando(comando);
                System.out.println("Servidor RMI: \n" + respuesta);
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    private static void mostrarComandos() {
        System.out.println("Escribe un comando:");
        System.out.println("  - listar (Muestra procesos)");
        System.out.println("  - iniciar <app> (Ejecuta una aplicación)");
        System.out.println("  - detener <pid> (Detiene un proceso)");
        System.out.println("  - monitorear <pid> (Muestra detalles de un proceso)");
        System.out.println("  - ayuda (Muestra este menú)");
        System.out.println("  - salir (Finaliza la conexión)");
    }
}