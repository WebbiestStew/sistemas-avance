import java.net.*;
import java.io.*;

public class ServidorMultiCliente {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Servidor esperando conexiones...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClienteHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClienteHandler extends Thread {
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String comando;
            while ((comando = entrada.readLine()) != null) {
                salida.println("Comando recibido: " + comando);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}