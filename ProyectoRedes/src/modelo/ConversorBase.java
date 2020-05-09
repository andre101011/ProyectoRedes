package modelo;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class ConversorBase {

	private String entrada;
	private String base;

	public ConversorBase(String entrada, String base) {
		this.entrada = entrada;
		this.base = base;
	}

	public static String stringToBinary(String text) {
		String bString = "";
		String temp = "";
		for (int i = 0; i < text.length(); i++) {
			temp = Integer.toBinaryString(text.charAt(i));
			for (int j = temp.length(); j < 8; j++) {
				temp = "0" + temp;
			}
			bString += temp + " ";
		}
		return bString;
	}

	public boolean isNumeric(String strNum) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}

	public String[] convertir() throws Exception {
		String[] resultados = new String[3];
		switch (base) {
		case "dec":
			if (entrada.matches("[0-9]+")) {
				BigInteger bigEntrada = new BigInteger(entrada, 10);
				resultados[0] = entrada.replaceAll("^0+(?!$)", "");
				resultados[1] = bigEntrada.toString(2);
				resultados[2] = new BigInteger(entrada).toString(16).toUpperCase();
			} else {
				throw new Exception("decimal");
			}
			break;

		case "bin":
			if (entrada.matches("^[0-1]+$")) {
				BigInteger bigEntrada = new BigInteger(entrada, 2);
				resultados[0] = bigEntrada + "";
				resultados[1] = entrada.replaceAll("^0+(?!$)", "");
				resultados[2] = bigEntrada.toString(16).toUpperCase();
			} else {
				throw new Exception("binario");
			}
			break;

		case "hex":
			if (entrada.matches("[0-9A-F]+")) {
				BigInteger bigEntrada = new BigInteger(entrada, 16);
				resultados[0] = bigEntrada + "";
				resultados[1] = bigEntrada.toString(2);
				resultados[2] = bigEntrada.toString(16).toUpperCase();
			} else {
				throw new Exception("hexadecimal");
			}
			break;
		}
		return resultados;
	}

}
