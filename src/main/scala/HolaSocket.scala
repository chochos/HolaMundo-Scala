package hello

import java.net.Socket
import java.io.{InputStream,OutputStream}

class HolaSocket(socket:Socket, proc:(InputStream,OutputStream) => String) extends Runnable {

	def run() = {
		println("Nos mandan " + proc(socket.getInputStream, socket.getOutputStream))
		socket.close
	}

}
