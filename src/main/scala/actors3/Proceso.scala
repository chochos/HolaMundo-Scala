package hello.actors3

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

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
