import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Middleware middleware = (Middleware) registro.lookup("Middleware");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Conectado al Middleware RMI. Escribe comandos:");

            while (true) {
                System.out.print("> ");
                String comando = scanner.nextLine();
                if ("salir".equalsIgnoreCase(comando)) {
                    System.out.println("Desconectando...");
                    break;
                }
                String respuesta = middleware.ejecutarComando(comando);
                System.out.println("Servidor: " + respuesta);
            }

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}