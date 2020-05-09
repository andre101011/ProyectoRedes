package modelo;

import java.util.Arrays;

public class CalculadoraIp {

	private String ipHost;
	private String ipRed;
	private String mascara;
	private int bitsHost;
	private int cantidadHosts;
	private int mascaraSimplificada;
	private String ipBroadcast;
	private String rangoUsable;

	public CalculadoraIp(String ipHost, String ipRed, int mascaraSimplificada) {
		this.ipHost = ipHost;
		this.mascaraSimplificada = mascaraSimplificada;
		this.mascara = convertirMascaraSimplificadaADecimal(mascaraSimplificada);
		if (ipRed.isEmpty()) {
			this.ipRed = encontraripRed();
		} else {
			this.ipRed = ipRed;
		}
		this.bitsHost = 32 - mascaraSimplificada;
		this.cantidadHosts = (int) Math.pow(2, bitsHost);
		this.ipBroadcast = encontraripBroadCast();
		this.rangoUsable = hallarRango();
	}

	public String encontraripRed() {
		int[] octetosNumericosIp = Arrays.stream(ipHost.split("\\.")).mapToInt(Integer::parseInt).toArray();
		int[] octetosNumericosMascara = Arrays.stream(mascara.split("\\.")).mapToInt(Integer::parseInt).toArray();
		String ipRed = "";
		for (int i = 0; i < octetosNumericosIp.length; i++) {
			ipRed += octetosNumericosIp[i] & octetosNumericosMascara[i];
			if (i < octetosNumericosIp.length - 1) {
				ipRed += ".";
			}
		}
		return ipRed;
	}

	public String encontraripBroadCast() {
		System.out.println(ipRed);
		int[] octetosNumericos = Arrays.stream(ipRed.split("\\.")).mapToInt(Integer::parseInt).toArray();

		String ipBroadcast = "";
		boolean flag = true;

		for (int i = octetosNumericos.length - 1; i >= 0; i--) {

			String octetoBinario = Integer.toBinaryString(octetosNumericos[i]);
			octetoBinario = rellenarPorIzquierda(octetoBinario, 8, '0');
			String nuevoOctetoBinario = "";
			for (int j = octetoBinario.length() - 1; j >= 0; j--) {

				if (bitsHost == 0) {
					flag = false;
				}
				if (flag) {
					nuevoOctetoBinario += "1";
				} else {
					nuevoOctetoBinario = octetoBinario.charAt(j) + nuevoOctetoBinario;
				}
				bitsHost--;
			}
			ipBroadcast = Integer.parseInt(nuevoOctetoBinario, 2) + ipBroadcast;
			if (i > 0)
				ipBroadcast = "." + ipBroadcast;

		}
		return ipBroadcast;
	}

	public String hallarRango() {
		String inicioRango = sumarleALaIp(ipRed, 1);
		String ipResultado = sumarleALaIp(inicioRango, cantidadHosts - 3);
		return inicioRango + " - " + ipResultado;
	}

	public static String rellenarPorIzquierda(String str, int n, char replacement) {
		return String.format("%1$" + n + "s", str).replace(' ', replacement);
	}

	private String[] intArrayToStringArray(int[] intArray) {
		String str = Arrays.toString(intArray).replaceAll("\\s+", "");
		String strArray[] = str.substring(1, str.length() - 1).split(",");
		return strArray;
	}

	private int[] devolverOctetos(String ip) {
		String[] octetos = ip.split("\\.");
		int[] octetosNumericos = new int[octetos.length];
		for (int i = 0; i < octetos.length; i++) {
			octetosNumericos[i] = Integer.parseInt(octetos[i]);
		}
		return octetosNumericos;
	}

	static String sumarBinarios(String a, String b) {

		String resultado = "";
		int s = 0;
		int i = a.length() - 1, j = b.length() - 1;
		while (i >= 0 || j >= 0 || s == 1) {

			s += ((i >= 0) ? a.charAt(i) - '0' : 0);
			s += ((j >= 0) ? b.charAt(j) - '0' : 0);

			resultado = (char) (s % 2 + '0') + resultado;

			s /= 2;
			i--;
			j--;
		}
		return resultado;
	}

	private String sumarleALaIp(String ipRed, int cantidad) {
		int[] octetosIP = devolverOctetos(ipRed);
		octetosIP[octetosIP.length - 1] = octetosIP[octetosIP.length - 1] + 1;
		String inicioRangoBinString = "";
		for (int octeto : octetosIP) {
			inicioRangoBinString += rellenarPorIzquierda(Integer.toBinaryString(octeto), 8, '0');
		}
		String ipRedBinaria = sumarBinarios(inicioRangoBinString, Integer.toBinaryString(cantidad - 1));
		String[] arraybinario = ipRedBinaria.split("(?<=\\G........)");
		int[] result = new int[4];
		for (int i = 0; i < arraybinario.length; i++) {
			result[i] = Integer.parseInt(arraybinario[i], 2);
		}
		return String.join(".", intArrayToStringArray(result));
	}

	private String convertirMascaraSimplificadaADecimal(int num) {
		long bits = 0;
		bits = 0xffffffff ^ (1 << 32 - num) - 1;
		String mascara = String.format("%d.%d.%d.%d", (bits & 0x0000000000ff000000L) >> 24,
				(bits & 0x0000000000ff0000) >> 16, (bits & 0x0000000000ff00) >> 8, bits & 0xff);
		return mascara;
	}

	public String getIpHost() {
		return ipHost;
	}

	public void setIpHost(String ipHost) {
		this.ipHost = ipHost;
	}

	public String getIpRed() {
		return ipRed;
	}

	public void setIpRed(String ipRed) {
		this.ipRed = ipRed;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public int getBitsHost() {
		return bitsHost;
	}

	public void setBitsHost(int bitsHost) {
		this.bitsHost = bitsHost;
	}

	public int getCantidadHosts() {
		return cantidadHosts;
	}

	public void setCantidadHosts(int cantidadHosts) {
		this.cantidadHosts = cantidadHosts;
	}

	public int getMascaraSimplificada() {
		return mascaraSimplificada;
	}

	public void setMascaraSimplificada(int mascaraSimplificada) {
		this.mascaraSimplificada = mascaraSimplificada;
	}

	public String getIpBroadcast() {
		return ipBroadcast;
	}

	public void setIpBroadcast(String ipBroadcast) {
		this.ipBroadcast = ipBroadcast;
	}

	public String getRangoUsable() {
		return rangoUsable;
	}

	public void setRangoUsable(String rangoUsable) {
		this.rangoUsable = rangoUsable;
	}

}
