package hola;
import java.net.*;
import java.io.*;

public class Lector implements Runnable {

  private final Socket s;
  private Lector(Socket socket) {
    s = socket;
  }
  public void run() {
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
      String nombre = input.readLine();
      //Echemosle la bronca de hacer otro hilo al escritor
      Escritor.escribe(nombre, s);
    } catch (IOException ex) {
      //Algo malo ocurrio que hacemos?
    }
  }

  public static void leer(Socket s) {
    new Thread(new Lector(s)).start();
  }

}
