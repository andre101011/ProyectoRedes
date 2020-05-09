package main;

import java.lang.StringBuilder;

public class Pruebas {

	public static void main(String[] args) {
		int a = 57333;
		String bin = dec_to_bin(a);
		System.out.println(bin);
		String hex = bin_to_hex(bin);
		System.out.println(hex);

	}

	private static String dec_to_bin(int n) {
		if (n == 0) {
			return "0";
		}
		String binario = "";
		while (n > 0) {
			int rem = n % 2;
			binario = rem + binario;
			n = n / 2;
		}
		return binario;
	}

	private static String dec_to_hex(int num) {
		int residuo;
		String hexadecimal = "";
		char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		while (num > 0) {
			residuo = num % 16;
			hexadecimal = hex[residuo] + hexadecimal;
			num = num / 16;
		}
		return hexadecimal;
	}

	public static int bin_to_dec(int binario) {
		int decimal = 0;
		int n = 0;
		while (true) {
			if (binario == 0) {
				break;
			} else {
				int aux = binario % 10;
				decimal += aux * Math.pow(2, n);
				binario = binario / 10;
				n++;
			}
		}
		return decimal;
	}

	public static String bin_to_hex(String binario) {
		return String.format("%X", Long.parseLong(binario, 2));
	}

}
