import java.rmi.registry.LocateRegistry; //permite buscar un registro en un servidor RMI
import java.rmi.registry.Registry; //permite acceder a un registro en un servidor RMI

public class Servidor {
    public static void main(String[] args) {
        try { //intenta ejecutar el código
            MiddlewareImpl middleware = new MiddlewareImpl(); //crea una instancia de MiddlewareImpl
            Registry registro = LocateRegistry.createRegistry(1099); //crea un registro en el puerto 1099
            registro.rebind("Middleware", middleware); //vincula el objeto middleware al registro con el nombre "Middleware"
            System.out.println("Servidor RMI en ejecución...");
        } catch (Exception e) { //captura errores
            System.out.println("Error en el servidor: " + e.getMessage()); //muestra el mensaje de error
        }
    }
}