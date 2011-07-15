package hello

import java.io.{ByteArrayInputStream,ByteArrayOutputStream}
import org.scalatest.junit.JUnitSuite
import org.junit.Test

class TestProc extends JUnitSuite {

	@Test
	def testProc() = {
		val input = new ByteArrayInputStream(String.format("Enrique%n").getBytes())
		val output = new ByteArrayOutputStream()
		val result = holaProc(input, output)
		assert (result == "Enrique")
		assert(output.toString().trim() == "Hola, Enrique!")
	}

}
