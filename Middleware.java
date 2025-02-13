import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Middleware extends Remote {
    String ejecutarComando(String comando) throws RemoteException;
}