import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteGestion {
    private static final String SERVIDOR = "127.0.0.1"; // Dirección del servidor
    private static final int PUERTO = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor.");
            mostrarComandos();

            while (true) {
                System.out.print("> ");
                String comando = scanner.nextLine();
                salida.println(comando);

                if ("salir".equalsIgnoreCase(comando)) {
                    System.out.println("Desconectando del servidor...");
                    break;
                }

                recibirRespuesta(entrada);
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    private static void recibirRespuesta(BufferedReader entrada) throws IOException {
        String respuesta;
        while ((respuesta = entrada.readLine()) != null) {
            System.out.println("Servidor: " + respuesta);
            if (!entrada.ready()) {
                break;
            }
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