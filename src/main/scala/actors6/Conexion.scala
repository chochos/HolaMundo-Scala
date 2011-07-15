package hello.actors6

import scala.actors.Actor
import scala.actors.Actor._
import java.io._
import java.net.Socket
import org.slf4j._

/** Este actor singleton recibe un socket y lee de ahi el texto, con un timeout
 * para no quedarse esperando indefinidamente.
 * Si recibe saludo, envia mensaje sincrono al Saludador, que genera y devuelve una cadena con el saludo.
 * Si el Saludador devuelve None, es porque recibio una despedida y entonces se comienza la secuencia de shutdown.
 *
 * @author Enrique Zamudio
 */
object Conexion extends Actor {

  private val log = LoggerFactory.getLogger(getClass)

  def act() {
    loop {
      react {
        case s:Socket =>
          val now = new java.util.Date
          log info "Recibimos conexion"
          val out = new PrintStream(s.getOutputStream)
          try {
            s.setSoTimeout(5000)
            val reader = new BufferedReader(new InputStreamReader(s.getInputStream))
            val nombre = reader.readLine()
            val saludo = Saludador !? Saluda(nombre, now)
            saludo match {
              case saludo:String => out println saludo
              case None =>
                Servidor.shutdown()
                exit()
              case wtf:Object => println("No se que hacer con {} tipo {}", wtf, wtf.getClass.getName)
              case wtf =>
              log.warn("que carajos es un {}", wtf)
            }
          } catch {
            case ex:java.net.SocketTimeoutException =>
              out println "Debes enviarme una linea de texto para devolverte un saludo"
            case ex:IOException =>
              log.error("Problemas en el socket", ex)
		  } finally {
            log info "cerrando socket"
            s.close()
		  }
      }
    }
  }
}
