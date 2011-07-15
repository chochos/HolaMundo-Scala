package hello.actors2

import java.net.ServerSocket

/** Este servidor es identico al anterior. */
object Servidor {

  def run(port:Int) = {
    val server = new ServerSocket(port)
    while (true) {
      val sock = server.accept
      println("Nueva conexion")
      Proceso ! sock
    }
  }

  def main(args:Array[String]) = {
    val port = if (args.length > 0) args(0).toInt else 9999
    println("Corriendo servidor en puerto " + port)
    Proceso.start
    run(port)
  }

}
