//Podemos definir paquetes de manera similar a los namespaces de C#; cuando lo hacemos asi, el paquete es un objeto dentro del cual
//podemos meter funciones de primer nivel
package object hello {

import java.io._

/** Aqui tenemos una funcion de primer nivel (disque)... realmente existe dentro del paquete hello, que es un objeto.
 * @author Enrique Zamudio
 */
def holaProc(inputStream:InputStream, outputStream:OutputStream) = {
	val input = new BufferedReader(new InputStreamReader(inputStream))
	val output = new PrintStream(outputStream)
	val nombre = input.readLine()
	output.printf("Hola, %s!%n", nombre)
	output.flush()
	nombre
}

}
