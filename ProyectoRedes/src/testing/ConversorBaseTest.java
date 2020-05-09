package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import modelo.ConversorBase;

class ConversorBaseTest {

	@Test
	void testEntradaDecimal() {
		try {
			ConversorBase tester = new ConversorBase("3", "dec");
			String[] resultados = tester.convertir();
			assertEquals("3", resultados[0]);
			assertEquals("11", resultados[1]);
			assertEquals("3", resultados[2]);

			ConversorBase tester2 = new ConversorBase("40", "dec");
			String[] resultados2 = tester2.convertir();
			assertEquals("40", resultados2[0]);
			assertEquals("101000", resultados2[1]);
			assertEquals("28", resultados2[2]);

			ConversorBase tester3 = new ConversorBase("6156513", "dec");
			String[] resultados3 = tester3.convertir();
			assertEquals("6156513", resultados3[0]);
			assertEquals("10111011111000011100001", resultados3[1]);
			assertEquals("5DF0E1", resultados3[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testEntradaBinaria() {
		try {
			ConversorBase tester = new ConversorBase("00011", "bin");
			String[] resultados = tester.convertir();
			assertEquals("3", resultados[0]);
			assertEquals("11", resultados[1]);
			assertEquals("3", resultados[2]);

			ConversorBase tester2 = new ConversorBase("101011100", "bin");
			String[] resultados2 = tester2.convertir();
			assertEquals("348", resultados2[0]);
			assertEquals("101011100", resultados2[1]);
			assertEquals("15C", resultados2[2]);

			ConversorBase tester3 = new ConversorBase("00111010110010010101010", "bin");
			String[] resultados3 = tester3.convertir();
			assertEquals("1926314", resultados3[0]);
			assertEquals("111010110010010101010", resultados3[1]);
			assertEquals("1D64AA", resultados3[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testEntradaHexadecimal() {
		try {
			ConversorBase tester = new ConversorBase("15", "hex");
			String[] resultados = tester.convertir();
			assertEquals("21", resultados[0]);
			assertEquals("10101", resultados[1]);
			assertEquals("15", resultados[2]);

			ConversorBase tester2 = new ConversorBase("AB515C", "hex");
			String[] resultados2 = tester2.convertir();
			assertEquals("11227484", resultados2[0]);
			assertEquals("101010110101000101011100", resultados2[1]);
			assertEquals("AB515C", resultados2[2]);

			ConversorBase tester3 = new ConversorBase("F515F11A", "hex");
			String[] resultados3 = tester3.convertir();
			assertEquals("4111855898", resultados3[0]);
			assertEquals("11110101000101011111000100011010", resultados3[1]);
			assertEquals("F515F11A", resultados3[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
