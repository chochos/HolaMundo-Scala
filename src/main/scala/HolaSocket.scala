package hello

import java.net.Socket
import java.io.{InputStream,OutputStream}

/** Esta clase maneja una conexion, para recibir el saludo y devolverlo.
 * @author Enrique Zamudio
 */
class HolaSocket(socket:Socket, proc:(InputStream,OutputStream) => String) extends Runnable {

	def run() = {
		println("Nos mandan " + proc(socket.getInputStream, socket.getOutputStream))
		socket.close
	}

}
