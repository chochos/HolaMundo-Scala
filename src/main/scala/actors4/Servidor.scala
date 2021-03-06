package hello.actors4

import java.net.ServerSocket

//Este es identico al de la version anterior.
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
