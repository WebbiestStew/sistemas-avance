import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            MiddlewareImpl middleware = new MiddlewareImpl();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("Middleware", middleware);
            System.out.println("Servidor RMI en ejecución...");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}