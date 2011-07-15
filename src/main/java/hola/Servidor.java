package hola;
import java.net.*;
import java.io.*;

public class Servidor {

  public static void main(String[] args) {
    try {
      ServerSocket server = new ServerSocket(9999);
      while (true) {
        Socket socket = server.accept();
        //Por ahora dejemosle la chamba de los hilos al lector
        Lector.leer(socket);
      }
    } catch (IOException ex) {
      System.out.println("Ha tronado!");
    }
  }
}
