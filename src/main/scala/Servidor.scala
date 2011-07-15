package hello

import java.net.ServerSocket

/** Este es el servidor mas simple. Primera version: Practicamente igual a lo que haria en Java.
 * @author Enrique Zamudio
 */
class Servidor(port:Int) {

	/** Esto es para poder detener el servidor de manera remota */
	var sigue = true

	def stop() {
		sigue = false
	}

	def run() = {
		val server = new ServerSocket(port)
		while (sigue) {
			val sock = server.accept
			println("Nueva conexion")
			val proc = new HolaSocket(sock, holaProc)
			new Thread(proc).start()
		}
	}
}

/** En Scala no hay metodos estaticos asi que tenemos que meter el main en un singleton */
object Main {

	def main(args:Array[String]) = {
		val port = if (args.length > 0) args(0).toInt else 9999
		println("Corriendo servidor en puerto " + port)
		new Servidor(port).run
	}

}
