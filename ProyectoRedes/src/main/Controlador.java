/**
 * Sample Skeleton for 'Interfaz.fxml' Controller Class
 */

package main;

import java.awt.print.Printable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
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

	@FXML // fx:id="txtFieldDireccionIP1"
	private TextField txtFieldDireccionIP1; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcular1"
	private Button btnCalcular1; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldDireccionRed2"
	private TextField txtFieldDireccionRed2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBroadcast2"
	private TextField txtFieldBroadcast2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldCantidadHosts2"
	private TextField txtFieldCantidadHosts2; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBitsRed"
	private TextField txtFieldBitsRed; // Value injected by FXMLLoader

	@FXML // fx:id="txtFieldBitsHosts"
	private TextField txtFieldBitsHosts; // Value injected by FXMLLoader

	@FXML // fx:id="btnVerLista"
	private Button btnVerLista; // Value injected by FXMLLoader

	@FXML
	private ChoiceBox<?> cBoxMascara;

	@FXML
	private ChoiceBox<?> cBoxMascara2;

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
		cBoxMascara.getSelectionModel().selectFirst();
		cBoxMascara2.getSelectionModel().selectFirst();

	}

	public void calcular21() {
		String ipHost = txtFieldDireccionIP.getText();
		if (validarIpConMascara(ipHost)) {

			int mascaraSimplificada = Integer
					.parseInt(cBoxMascara.getSelectionModel().getSelectedItem().toString().substring(1));
			if (mascaraSimplificada <= 30) {
				String mascaraDecimal = convertirMascaraSimplificadaADecimal(mascaraSimplificada);
				String direccionRed = encontrarDireccionRed(ipHost, mascaraDecimal);
				txtFieldDireccionRed.setText(direccionRed);

				int bitsHost = 32 - mascaraSimplificada;
				String direccionBroadcast = encontrarDireccionBroadCast(direccionRed, bitsHost);
				txtFieldBroadcast.setText((direccionBroadcast));
				int cantidadHosts = (int) Math.pow(2, bitsHost);
				txtFieldCantidadHosts.setText(cantidadHosts + "");

				int[] octetosIP = devolverOctetos(direccionRed);
				int[] octetosMascara = devolverOctetos(direccionBroadcast);
				txtFieldRangoHosts.setText(octetosIP[0] + "." + octetosIP[1] + "." + octetosIP[2] + "."
						+ (octetosIP[3] + 1) + " -  " + octetosMascara[0] + "." + octetosMascara[1] + "."
						+ octetosMascara[2] + "." + (octetosMascara[3] - 1));
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING, "IP incorrecta", ButtonType.OK);
			alert.showAndWait();
		}
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
			octetoBinario = padLeft(octetoBinario, 8, '0');
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

	private static boolean validarIpConMascara(final String ip) {
		String patron = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
		return ip.matches(patron);
	}

	public static String padLeft(String str, int n, char replacement) {
		return String.format("%1$" + n + "s", str).replace(' ', replacement);
	}

	public static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}

}
