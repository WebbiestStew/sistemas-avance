import java.rmi.server.UnicastRemoteObject; //extender de esta clase para crear un objeto remoto
import java.rmi.RemoteException; //se lanza si ocurre un error de red o comunicación entre el cliente y el servidor RMI

public class MiddlewareImpl extends UnicastRemoteObject implements Middleware {
    public MiddlewareImpl() throws RemoteException { //constructor que lanza una excepción si ocurre un error de red o comunicación
        super(); //llama al constructor de la clase padre
    }

    @Override //sobreescribe el método de la interfaz Middleware
    public String ejecutarComando(String comando) throws RemoteException { //método que se ejecutará en el servidor
        System.out.println("Comando recibido: " + comando); 
        // Aquí puedes ejecutar comandos en el servidor y devolver la respuesta
        return "Ejecutado: " + comando;
    }
}