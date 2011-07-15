package hello.actors3

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

/** En esta version partimos la tarea en dos actores.
 * Este actor arranca, echa a andar el Lector y se queda esperando su mensaje.
 * @author Enrique Zamudio
 */
class Proceso(socket:java.net.Socket) extends Actor {

  def act() {
    val lector = new Lector(socket.getInputStream, this)
    lector.start
    loop {
      react {
        case nombre:String =>
          println ("Nos enviaron " + nombre)
          val out = new PrintStream(socket.getOutputStream)
          out.printf("Hola, %s!%n", nombre)
          out.flush
          socket.close
          exit
      }
    }
  }
}
