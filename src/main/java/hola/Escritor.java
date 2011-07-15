package hola;
import java.net.Socket;
import java.io.*;

public class Escritor {

  public static void escribe(String nombre, Socket sock) throws IOException {
    PrintStream pout = new PrintStream(sock.getOutputStream());
    pout.printf("Hola, %s!%n", nombre);
    pout.flush();
    sock.close();
  }

}
