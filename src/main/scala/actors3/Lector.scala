package hello.actors3

import scala.actors.Actor
import scala.actors.Actor._
import java.io._

/** En esta version partimos la tarea en dos actores.
 * Este es el Lector, que solamente lee del socket y le pasa a otro actor lo leido
 * para que continue con el trabajo.
 * @author Enrique Zamudio
 */
class Lector(input:InputStream, proc:Proceso) extends Actor {

  def act() {
    val reader = new BufferedReader(new InputStreamReader(input))
    val nombre = reader.readLine
    proc ! nombre
    exit
  }
}
