package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.CalculadoraIp;

class CalculadoraIpTest {
/*
 * ===============================Tests del punto 2.1===============================================
 */
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
	void testEncontraripBroadcastConHost() {
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
	void testHallarCantidadDeHosts() {
		CalculadoraIp tester = new CalculadoraIp("192.168.1.1", "", 24);
		assertEquals(254, tester.getCantidadHosts());
		CalculadoraIp tester2 = new CalculadoraIp("192.168.1.1", "", 10);
		assertEquals(4194302, tester2.getCantidadHosts());
		CalculadoraIp tester3 = new CalculadoraIp("192.168.1.1", "", 15);
		assertEquals(131070, tester3.getCantidadHosts());
		CalculadoraIp tester4 = new CalculadoraIp("192.168.1.1", "", 20);
		assertEquals(4094, tester4.getCantidadHosts());
	}

	@Test
	void testHallarRangoConHost() {
		CalculadoraIp tester = new CalculadoraIp("192.168.1.1", "", 24);
		assertEquals("192.168.1.1 - 192.168.1.254", tester.hallarRango());
		CalculadoraIp tester2 = new CalculadoraIp("192.168.0.255", "", 30);
		assertEquals("192.168.0.253 - 192.168.0.254", tester2.hallarRango());
		CalculadoraIp tester3 = new CalculadoraIp("177.87.45.55", "", 17);
		assertEquals("177.87.0.1 - 177.87.127.254", tester3.hallarRango());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.0.1 - 177.87.127.254", tester4.hallarRango());
	}
	/*
	 * ===============================Tests del punto 2.2===============================================
	 */
	
	
	@Test
	void testHallarMascaraSubRedDecimal() {
		CalculadoraIp tester = new CalculadoraIp("", "192.168.1.0", 24);
		assertEquals("255.255.255.0", tester.getMascara());
		CalculadoraIp tester1 = new CalculadoraIp("", "192.168.0.0", 30);
		assertEquals("255.255.255.252", tester1.getMascara());
		CalculadoraIp tester2 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("255.255.128.0", tester2.getMascara());
		CalculadoraIp tester3 = new CalculadoraIp("", "177.0.0.0", 10);
		assertEquals("255.192.0.0", tester3.getMascara());
		
	}
	
	@Test
	void testEncontraripBroadcastConIpRed() {
		CalculadoraIp tester = new CalculadoraIp("", "192.168.1.0", 24);
		assertEquals("192.168.1.255", tester.getIpBroadcast());
		CalculadoraIp tester2 = new CalculadoraIp("", "192.168.0.0", 30);
		assertEquals("192.168.0.3", tester2.encontraripBroadCast());
		CalculadoraIp tester3 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.127.255", tester3.getIpBroadcast());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.0.0.0", 10);
		assertEquals("177.63.255.255", tester4.getIpBroadcast());
	}
	
	@Test
	void testEncontrarCantidadBitsDeRed() {
		CalculadoraIp tester = new CalculadoraIp("", "192.168.1.0", 24);
		assertEquals(24, tester.getMascaraSimplificada());
		CalculadoraIp tester2 = new CalculadoraIp("", "192.168.0.0", 30);
		assertEquals(30, tester2.getMascaraSimplificada());
		CalculadoraIp tester3 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals(17, tester3.getMascaraSimplificada());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.0.0.0", 10);
		assertEquals(10, tester4.getMascaraSimplificada());
	}
	
	@Test
	void testEncontrarCantidadBitsDeHost() {
		CalculadoraIp tester = new CalculadoraIp("", "192.168.1.0", 24);
		assertEquals(8, tester.getBitsHost());
		CalculadoraIp tester2 = new CalculadoraIp("", "192.168.0.0", 30);
		assertEquals(2, tester2.getBitsHost());
		CalculadoraIp tester3 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals(15, tester3.getBitsHost());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.0.0.0", 10);
		assertEquals(22, tester4.getBitsHost());
	}
	
	@Test
	void testHallarRangoConIpRed() {
		CalculadoraIp tester = new CalculadoraIp("", "192.168.1.0", 24);
		assertEquals("192.168.1.1 - 192.168.1.254", tester.hallarRango());
		CalculadoraIp tester2 = new CalculadoraIp("", "192.168.0.252", 30);
		assertEquals("192.168.0.253 - 192.168.0.254", tester2.hallarRango());
		CalculadoraIp tester3 = new CalculadoraIp("", "177.87.0.0", 17);
		assertEquals("177.87.0.1 - 177.87.127.254", tester3.hallarRango());
		CalculadoraIp tester4 = new CalculadoraIp("", "177.64.0.0", 10);
		assertEquals("177.64.0.1 - 177.127.255.254", tester4.hallarRango());
	}
}
