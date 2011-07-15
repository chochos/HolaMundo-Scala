package hello.actors5

import scala.actors.Actor
import scala.actors.Actor._
import java.io._
import java.net.Socket

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
