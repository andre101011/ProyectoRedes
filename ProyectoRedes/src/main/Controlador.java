/**
 * Sample Skeleton for 'Interfaz.fxml' Controller Class
 */

package main;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controlador implements Initializable {

	@FXML // fx:id="txtFieldDireccionIP"
	private TextField txtFieldDireccionIP; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcular"
	private Button btnCalcular; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldDireccionRed"
	private TextField txtFieldDireccionRed; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBroadcast"
	private TextField txtFieldBroadcast; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldCantidadHosts"
	private TextField txtFieldCantidadHosts; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldRangoHosts"
	private TextField txtFieldRangoHosts; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldRangoHosts2"
	private TextField txtFieldRangoHosts2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldDireccionIP1"
	private TextField txtFieldDireccionIP2; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcular2"
	private Button btnCalcular2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldMascara"
	private TextField txtFieldMascara; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBroadcast2"
	private TextField txtFieldBroadcast2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldCantidadHosts2"
	private TextField txtFieldCantidadHosts2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBitsRed"
	private TextField txtFieldBitsRed; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBitsHosts"
	private TextField txtFieldBitsHosts; // Value injected by FXMLLoader

	@FXML
	private ChoiceBox<?> cBoxMascara;

	@FXML
	private ChoiceBox<?> cBoxMascara2;

	@FXML // fx:id="txtFieldEntrada"
	private TextField txtFieldEntrada; // Value injected by FXMLLoader

	@FXML // fx:id="cBoxBase"
	private ChoiceBox<?> cBoxBase; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldDecimal"
	private TextField txtFieldDecimal; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBinario"
	private TextField txtFieldBinario; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldhexa"
	private TextField txtFieldhexa; // Value injected by FXMLLoader

	@FXML // fx:id="btnConvertir"
	private Button btnConvertir; // Value injected by FXMLLoader

	@Override

	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> list = new ArrayList<String>();

		for (int i = 8; i <= 30; i++) {
			list.add("/" + i);
		}

		ObservableList obList = FXCollections.observableList(list);
		cBoxMascara.getItems().clear();
		cBoxMascara2.getItems().clear();
		cBoxMascara.setItems(obList);
		cBoxMascara2.setItems(obList);
		cBoxMascara.getSelectionModel().select(16);
		cBoxMascara2.getSelectionModel().select(16);

		List<String> listaBases = new ArrayList<String>();

		listaBases.add("dec");
		listaBases.add("bin");
		listaBases.add("hex");

		ObservableList obListBases = FXCollections.observableList(listaBases);
		cBoxBase.getItems().clear();
		cBoxBase.setItems(obListBases);
		cBoxBase.getSelectionModel().selectFirst();
		;

	}

	public void calcular21() {
		String ipHost = txtFieldDireccionIP.getText();
		if (validarFormatoIp(ipHost)) {

			int mascaraSimplificada = Integer
					.parseInt(cBoxMascara.getSelectionModel().getSelectedItem().toString().substring(1));

			String mascaraDecimal = convertirMascaraSimplificadaADecimal(mascaraSimplificada);
			String direccionRed = encontrarDireccionRed(ipHost, mascaraDecimal);
			txtFieldDireccionRed.setText(direccionRed);

			int bitsHost = 32 - mascaraSimplificada;
			String direccionBroadcast = encontrarDireccionBroadCast(direccionRed, bitsHost);
			txtFieldBroadcast.setText((direccionBroadcast));
			int cantidadHosts = (int) Math.pow(2, bitsHost);
			txtFieldCantidadHosts.setText(cantidadHosts + "");

			txtFieldRangoHosts.setText(hallarRango(direccionRed, cantidadHosts));

		} else {
			Alert alert = new Alert(AlertType.WARNING, "IP incorrecta", ButtonType.OK);
			alert.setContentText("La dirección ip ingresada no cumple con el formato IPv4");
			alert.showAndWait();
		}
	}

	public void calcular22() {
		String direccionRed = txtFieldDireccionIP2.getText();

		int mascaraSimplificada = Integer
				.parseInt(cBoxMascara2.getSelectionModel().getSelectedItem().toString().substring(1));
		String mascaraDecimal = convertirMascaraSimplificadaADecimal(mascaraSimplificada);

		if (validarFormatoIp(direccionRed)) {

			String direccionRedCalculada = encontrarDireccionRed(direccionRed, mascaraDecimal);
			if (direccionRed.equals(direccionRedCalculada)) {

				txtFieldMascara.setText(mascaraDecimal);
				txtFieldBitsRed.setText(mascaraSimplificada + "");
				int bitsHost = 32 - mascaraSimplificada;
				txtFieldBitsHosts.setText(bitsHost + "");
				String direccionBroadcast = encontrarDireccionBroadCast(direccionRed, bitsHost);
				txtFieldBroadcast2.setText((direccionBroadcast));
				int cantidadHosts = (int) Math.pow(2, bitsHost);
				txtFieldCantidadHosts2.setText(cantidadHosts + "");

				txtFieldRangoHosts2.setText(hallarRango(direccionRed, cantidadHosts));

			} else {
				Alert alert = new Alert(AlertType.WARNING, "Direccion IP incorrecta", ButtonType.OK);
				alert.setContentText(
						"Verifique que la ip sea una dirección de RED y que corresponda con la mascara seleccionada \n ¿Quiso decir "
								+ direccionRedCalculada + " ?");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING, "IP incorrecta", ButtonType.OK);
			alert.setContentText("La dirección ip ingresada no cumple con el formato IPv4");
			alert.showAndWait();
		}
	}

	public void convertir() {
		String entrada = txtFieldEntrada.getText();
		switch (cBoxBase.getSelectionModel().getSelectedItem().toString()) {
		case "dec":
			if (entrada.matches("[0-9]+")) {
				txtFieldDecimal.setText(entrada.replaceAll("^0+(?!$)", ""));
				txtFieldBinario.setText(stringToBinary(entrada));
				txtFieldhexa.setText(new BigInteger(entrada).toString(16).toUpperCase());
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Numero invalido", ButtonType.OK);
				alert.setContentText("Verifique que haya ingresado un numero decimal");
				alert.showAndWait();
			}
			break;

		case "bin":
			if (entrada.matches("^[0-1]+$")) {
				entrada = Integer.parseInt(entrada, 2) + "";
				txtFieldDecimal.setText(entrada + "");
				txtFieldBinario.setText(stringToBinary(entrada));
				txtFieldhexa.setText(new BigInteger(entrada).toString(16).toUpperCase());
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Numero invalido", ButtonType.OK);
				alert.setContentText("Verifique que haya ingresado un numero binario");
				alert.showAndWait();
			}
			break;

		case "hex":
			if (entrada.matches("[0-9A-F]+")) {
				BigInteger bigEntrada = new BigInteger(entrada, 16);
				txtFieldDecimal.setText(bigEntrada + "");
				txtFieldBinario.setText(bigEntrada.toString(2));
				txtFieldhexa.setText(new BigInteger(entrada).toString(16).toUpperCase());
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Numero invalido", ButtonType.OK);
				alert.setContentText("Verifique que haya ingresado un numero hexadecimal");
				alert.showAndWait();
			}
			break;
		}
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

	private String hallarRango(String ip, int cantidadHosts) {

		String inicioRango = sumarleALaIp(ip, 1);
		String ipResultado = sumarleALaIp(inicioRango, cantidadHosts - 3);

		return inicioRango + " - " + ipResultado;
	}

	private String sumarleALaIp(String direccionRed, int cantidad) {
		int[] octetosIP = devolverOctetos(direccionRed);
		octetosIP[octetosIP.length - 1] = octetosIP[octetosIP.length - 1] + 1;
		String inicioRangoBinString = "";
		for (int octeto : octetosIP) {
			inicioRangoBinString += rellenarPorIzquierda(Integer.toBinaryString(octeto), 8, '0');
		}
		String direccionRedBinaria = sumarBinarios(inicioRangoBinString, Integer.toBinaryString(cantidad - 1));
		String[] arraybinario = direccionRedBinaria.split("(?<=\\G........)");
		int[] result = new int[4];
		for (int i = 0; i < arraybinario.length; i++) {
			result[i] = Integer.parseInt(arraybinario[i], 2);
		}
		return String.join(".", intArrayToStringArray(result));
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

	private String encontrarDireccionBroadCast(String direccionRed, int bitshost) {
		int[] octetosNumericos = Arrays.stream(direccionRed.split("\\.")).mapToInt(Integer::parseInt).toArray();

		String direccionBroadcast = "";
		boolean flag = true;

		for (int i = octetosNumericos.length - 1; i >= 0; i--) {

			String octetoBinario = Integer.toBinaryString(octetosNumericos[i]);
			octetoBinario = rellenarPorIzquierda(octetoBinario, 8, '0');
			String nuevoOctetoBinario = "";
			for (int j = octetoBinario.length() - 1; j >= 0; j--) {

				if (bitshost == 0) {
					flag = false;
				}
				if (flag) {
					nuevoOctetoBinario += "1";
				} else {
					nuevoOctetoBinario = octetoBinario.charAt(j) + nuevoOctetoBinario;
				}
				bitshost--;
			}
			direccionBroadcast = Integer.parseInt(nuevoOctetoBinario, 2) + direccionBroadcast;
			if (i > 0)
				direccionBroadcast = "." + direccionBroadcast;

		}
		return direccionBroadcast;
	}

	private String encontrarDireccionRed(String ipHost, String mascara) {

		int[] octetosNumericosIp = Arrays.stream(ipHost.split("\\.")).mapToInt(Integer::parseInt).toArray();
		int[] octetosNumericosMascara = Arrays.stream(mascara.split("\\.")).mapToInt(Integer::parseInt).toArray();
		String direccionRed = "";
		for (int i = 0; i < octetosNumericosIp.length; i++) {
			direccionRed += octetosNumericosIp[i] & octetosNumericosMascara[i];
			if (i < octetosNumericosIp.length - 1) {
				direccionRed += ".";
			}
		}
		return direccionRed;
	}

	private String convertirMascaraSimplificadaADecimal(int num) {
		long bits = 0;
		bits = 0xffffffff ^ (1 << 32 - num) - 1;
		String mascara = String.format("%d.%d.%d.%d", (bits & 0x0000000000ff000000L) >> 24,
				(bits & 0x0000000000ff0000) >> 16, (bits & 0x0000000000ff00) >> 8, bits & 0xff);
		return mascara;
	}

	private static boolean validarFormatoIp(final String ip) {
		String patron = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
		return ip.matches(patron);
	}

	public static String rellenarPorIzquierda(String str, int n, char replacement) {
		return String.format("%1$" + n + "s", str).replace(' ', replacement);
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
}
