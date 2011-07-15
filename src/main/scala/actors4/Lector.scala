package hello.actors4

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

//Este Lector usa una case class en vez de mandar solamente la cadena leida.
class Lector(input:InputStream, proc:Proceso) extends Actor {

  def act() {
    val reader = new BufferedReader(new InputStreamReader(input))
    val nombre = reader.readLine
    proc ! Saluda(nombre, new java.util.Date())
    exit
  }
}
