import java.rmi.registry.LocateRegistry; //permite buscar un registro en un servidor RMI
import java.rmi.registry.Registry;  //permite acceder a un registro en un servidor RMI
import java.util.Scanner; //Se usa para leer la entrada del usuario desde la consola

public class ClienteRMI {
    public static void main(String[] args) {
        try { //intenta ejecutar el código
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099); //busca el registro en la dirección y puerto especificados
            Middleware middleware = (Middleware) registro.lookup("Middleware"); //busca el objeto Middleware en el registro

            Scanner scanner = new Scanner(System.in); //se usa pra leer la entrada al usuario
            System.out.println("Conectado al Middleware RMI. Escribe comandos:"); //muestra mensaje de conexión exitosa

            while (true) { //bucle infinito que espera respuesta del usuario
                System.out.print("> "); //muestra el prompt
                String comando = scanner.nextLine(); //lee la entrada del usuario
                if ("salir".equalsIgnoreCase(comando)) { //si se escribe salir, se desconecta del servidor
                    System.out.println("Desconectando..."); //muestra mensaje de desconexión
                    break; //sale del bucle
                }
                String respuesta = middleware.ejecutarComando(comando); //ejecuta el comando en el servidor
                System.out.println("Servidor: " + respuesta); //muestra la respuesta del servidor
            }

        } catch (Exception e) { //captura errores
            System.out.println("Error en el cliente: " + e.getMessage()); //muestra el mensaje de error
        }
    }
}