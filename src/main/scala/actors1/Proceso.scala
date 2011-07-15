package hello.actors1

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

class Proceso(socket:java.net.Socket) extends Actor {

  def act() {
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
    val nombre = reader.readLine
    println ("Nos enviaron " + nombre)
    val out = new PrintStream(socket.getOutputStream)
    out.printf("Hola, %s!%n", nombre)
    out.flush
    socket.close
  }
}
