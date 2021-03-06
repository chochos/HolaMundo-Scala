package hello.actors6

import scala.actors.Actor
import scala.actors.Actor._
import java.io._
import org.slf4j._

/** Este actor es nuevo, simplemente genera un saludo, PERO si recibe una cadena que parezca
 * una despedida entonces termina y devuelve None.
 *
 * @author Enrique Zamudio
 */
object Saludador extends Actor {

  private val log = LoggerFactory.getLogger(getClass())
  private val Bye = """(bye|adios|au revoir).*""".r

  def act() = {
    loop {
      react {
        case Saluda(Bye(despedida),_) =>
          reply(None)
          log.info("ADIOS ({})!!!", despedida)
          exit()
        case Saluda(nom,cuando) =>
          log.info("Formateamos saludo recibido {} a las {}", cuando, new java.util.Date)
          reply(String.format("Hola, %s!", nom))
      }
    }
  }
}
