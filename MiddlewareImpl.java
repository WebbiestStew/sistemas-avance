import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;

public class MiddlewareImpl extends UnicastRemoteObject implements Middleware {
    public MiddlewareImpl() throws RemoteException {
        super();
    }

    @Override
    public String ejecutarComando(String comando) throws RemoteException {
        System.out.println("Comando recibido: " + comando);

        try {
            if (comando.equalsIgnoreCase("listar")) {
                return obtenerProcesos();
            } else if (comando.startsWith("iniciar ")) {
                String app = comando.substring(8);
                return ejecutar("cmd /c start " + app); // Windows
                // return ejecutar(app + " &"); // Linux
            } else if (comando.startsWith("detener ")) {
                String pid = comando.substring(8);
                return ejecutar("taskkill /PID " + pid + " /F"); // Windows
                // return ejecutar("kill -9 " + pid); // Linux
            } else if (comando.startsWith("monitorear ")) {
                String pid = comando.substring(11);
                return ejecutar("tasklist /FI \"PID eq " + pid + "\""); // Windows
                // return ejecutar("top -p " + pid); // Linux
            } else if (comando.equalsIgnoreCase("ayuda")) {
                return "Comandos disponibles:\n- listar\n- iniciar <app>\n- detener <pid>\n- monitorear <pid>\n- salir";
            } else {
                return "Comando no reconocido.";
            }
        } catch (Exception e) {
            return "Error al ejecutar comando: " + e.getMessage();
        }
    }

    private String obtenerProcesos() throws IOException {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        String comando;
        if (sistemaOperativo.contains("win")) {
            comando = "tasklist"; // Windows
        } else {
            comando = "ps -aux"; // Linux/macOS
        }
        return ejecutar(comando);
    }

    private String ejecutar(String comando) throws IOException {
        Process proceso = Runtime.getRuntime().exec(comando);
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        StringBuilder salida = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            salida.append(linea).append("\n");
        }
        return salida.toString();
    }
}