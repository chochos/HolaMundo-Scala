package hello.actors5

import scala.actors.Actor
import scala.actors.Actor._
import java.io._
import java.net.Socket

//Este actor singleton recibe un socket, lee el texto y envia lo leido al Escritor
//Ahora la case class trae mas datos (incluye el socket)
object Lector extends Actor {

  def act() {
    loop {
      react {
        case s:Socket =>
          val reader = new BufferedReader(new InputStreamReader(s.getInputStream))
          val cuando = new java.util.Date
          val nombre = reader.readLine()
          Escritor ! Saluda(nombre, cuando, s)
      }
    }
  }
}
