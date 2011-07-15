package hello.actors3

import java.net.ServerSocket

//El servidor sigue sin cambiar desde hace dos versiones.
package hello.actors3

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

//Este servidor crea un Proceso por socket y lo arranca (es un actor)
object Servidor {

  def run(port:Int) = {
    val server = new ServerSocket(port)
    while (true) {
      val sock = server.accept
      println("Nueva conexion")
      new Proceso(sock).start
    }
  }

  def main(args:Array[String]) = {
    val port = if (args.length > 0) args(0).toInt else 9999
    println("Corriendo servidor en puerto " + port)
    run(port)
  }

}
