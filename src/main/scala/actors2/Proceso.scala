package hello.actors2

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

object Proceso extends Actor {

  def act() {
    loop {
      react {
        case socket:java.net.Socket =>
          val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
          val nombre = reader.readLine
          println ("Nos enviaron " + nombre)
          val out = new PrintStream(socket.getOutputStream)
          out.printf("Hola, %s!%n", nombre)
          out.flush
          socket.close
      }
    }
  }
}
