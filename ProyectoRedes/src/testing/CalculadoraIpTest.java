package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.CalculadoraIp;

class CalculadoraIpTest {

	@Test
	void testEncontraripRed() {
		CalculadoraIp tester = new CalculadoraIp("192.168.1.1", "", 24);
		assertEquals("192.168.1.0", tester.getIpRed());
		CalculadoraIp tester2 = new CalculadoraIp("192.168.0.255", "", 30);
		assertEquals("192.168.0.252", tester2.getIpRed());
		CalculadoraIp tester3 = new CalculadoraIp("177.87.45.55", "", 17);
		assertEquals("177.87.0.0", tester3.getIpRed());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.0.0", tester4.getIpRed());
	}

	@Test
	void testEncontraripBroadcast() {
		CalculadoraIp tester = new CalculadoraIp("192.168.1.1", "", 24);
		assertEquals("192.168.1.255", tester.getIpBroadcast());
		CalculadoraIp tester2 = new CalculadoraIp("192.168.0.255", "", 30);
		assertEquals("192.168.0.255", tester2.encontraripBroadCast());
		CalculadoraIp tester3 = new CalculadoraIp("177.87.45.55", "", 17);
		assertEquals("177.87.127.255", tester3.getIpBroadcast());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.127.255", tester4.getIpBroadcast());
	}

	@Test
	void testHallarRango() {
		CalculadoraIp tester = new CalculadoraIp("192.168.1.1", "", 24);
		assertEquals("192.168.1.1 - 192.168.1.254", tester.hallarRango());
		CalculadoraIp tester2 = new CalculadoraIp("192.168.0.255", "", 30);
		assertEquals("192.168.0.253 - 192.168.0.254", tester2.hallarRango());
		CalculadoraIp tester3 = new CalculadoraIp("177.87.45.55", "", 17);
		assertEquals("177.87.0.1 - 177.87.127.254", tester3.hallarRango());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.0.1 - 177.87.127.254", tester4.hallarRango());
	}


}
