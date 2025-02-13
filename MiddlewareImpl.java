import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class MiddlewareImpl extends UnicastRemoteObject implements Middleware {
    public MiddlewareImpl() throws RemoteException {
        super();
    }

    @Override
    public String ejecutarComando(String comando) throws RemoteException {
        System.out.println("Comando recibido: " + comando);
        // Aquí puedes ejecutar comandos en el servidor y devolver la respuesta
        return "Ejecutado: " + comando;
    }
}