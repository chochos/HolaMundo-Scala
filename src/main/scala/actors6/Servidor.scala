package hello.actors6

import java.net.ServerSocket
import org.slf4j._

case class Saluda(nombre:String, fecha:java.util.Date)

/** Este es el servidor mas complicado hasta ahora: ahora si ya se puede cerrar
 * invocando un metodo. Sigue usando actores singleton, pero ya tenemos manejo de excepciones.
 *
 * @author Enrique Zamudio
 */
object Servidor {

  val log = LoggerFactory.getLogger(getClass())
  var server:ServerSocket = null
  var running=true

  def run(port:Int) = {
    //Tenemos que iniciar estos a patin
    Conexion.start()
    Saludador.start()
    try {
      server = new ServerSocket(port)
      while (running) {
        val sock = server.accept()
        log.info("Nueva conexion")
        Conexion ! sock
      }
    } catch {
      case _:java.net.BindException =>
        log.error("Otro proceso ya tiene ocupado el puerto {}", port)
      case ex:java.net.SocketException =>
        if ("Socket closed" == ex.getMessage) {
          log.warn("Han cerrado el server socket")
        } else {
          log.error("Error de socket", ex)
        }
      case e=>log.error("ALGO MALO",e)
    }
  }

  def shutdown() = {
    running=false
    server.close()
  }

  def main(args:Array[String]) = {
    val port = if (args.length > 0) args(0).toInt else 9999
    log.info("Corriendo servidor en puerto {}", port)
    run(port)
  }

}
