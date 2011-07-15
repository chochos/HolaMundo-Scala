package hello.actors5

import java.net.{ServerSocket,Socket}

case class Saluda(nombre:String, fecha:java.util.Date, socket:Socket)

object Servidor {

  def run(port:Int) = {
    val server = new ServerSocket(port)
    //Tenemos que iniciar estos a patin
    Lector.start
    Escritor.start
    while (true) {
      val sock = server.accept
      println("Nueva conexion")
      Lector ! sock
    }
  }

  def main(args:Array[String]) = {
    val port = if (args.length > 0) args(0).toInt else 9999
    println("Corriendo servidor en puerto " + port)
    run(port)
  }

}
