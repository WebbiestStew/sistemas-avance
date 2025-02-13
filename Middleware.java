import java.rmi.Remote; //la interfaz se usara en todos los metodos remotos
import java.rmi.RemoteException; //se lanza si ocurre un error de red o comunicación entre el cliente y el servidor RMI

public interface Middleware extends Remote { //extiende Remote para que sus métodos puedan llamarse desde otro equipo a través de la red
    String ejecutarComando(String comando) throws RemoteException; //método que se ejecutará en el servidor
}