import java.io.*; //Proporciona clases para entrada/salida
import java.net.*; //Contiene clases para trabajar con redes y sockets
import java.util.Scanner; //Se usa para leer la entrada del usuario desde la consola

public class ClienteGestion {
    private static final String SERVIDOR = "127.0.0.1"; // Dirección del servidor
    private static final int PUERTO = 8080; //Es el puerto en el que el servidor está escuchando conexiones

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO); //intenta conectar al cliente con el servidor
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); //permite leer datos que llegan al servidor
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); //Permite enviar datos al servidor.
             Scanner scanner = new Scanner(System.in)) { //se usa pra leer la entrada al usuario

            System.out.println("Conectado al servidor.");
            mostrarComandos();

            while (true) { //bucle infinito que espera respuesta del usuario
                System.out.print("> ");
                String comando = scanner.nextLine();
                salida.println(comando);

                if ("salir".equalsIgnoreCase(comando)) { //si se escribe salir, se desconecta del servidor
                    System.out.println("Desconectando del servidor...");
                    break;
                }

                recibirRespuesta(entrada);
            }
        } catch (IOException e) { //captura errores de entrada/salida
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