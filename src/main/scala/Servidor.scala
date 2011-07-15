package hello

import java.net.ServerSocket

class Servidor(port:Int) {

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

object Main {

	def main(args:Array[String]) = {
		val port = if (args.length > 0) args(0).toInt else 9999
		println("Corriendo servidor en puerto " + port)
		new Servidor(port).run
	}

}
