package hello.actors5

import scala.actors.Actor
import scala.actors.Actor._
import java.io._
import java.net.Socket

object Escritor extends Actor {

  def act() {
    loop {
      react {
        case s:Socket =>
          Lector ! s
        case Saluda(nom,cuando,socket) =>
          println("Nos enviaron '" + nom + "' a las " + cuando + " (ahorita son las " + new java.util.Date + ")")
          val out = new PrintStream(socket.getOutputStream)
          out.printf("Hola, %s!%n", nom)
          out.flush()
          socket.close()
      }
    }
  }
}
