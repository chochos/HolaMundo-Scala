package object hello {

import java.io._

def holaProc(inputStream:InputStream, outputStream:OutputStream) = {
	val input = new BufferedReader(new InputStreamReader(inputStream))
	val output = new PrintStream(outputStream)
	val nombre = input.readLine()
	output.printf("Hola, %s!%n", nombre)
	output.flush()
	nombre
}

}
