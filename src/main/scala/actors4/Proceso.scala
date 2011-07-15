package hello.actors4

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

class Proceso(socket:java.net.Socket) extends Actor {

  def respond(nombre:String) {
    val out = new PrintStream(socket.getOutputStream)
    out.printf("Hola, %s!%n", nombre)
    out.flush
    socket.close
  }

  def act() {
    val lector = new Lector(socket.getInputStream, this)
    lector.start
    loop {
      react {
        case nombre:String =>
          println ("Nos enviaron " + nombre)
          respond(nombre)
          exit
        case Saluda(nombre,fecha) =>
          println("Nos enviaron '" + nombre + "' a las " + fecha)
          respond(nombre)
          exit
      }
    }
  }
}
