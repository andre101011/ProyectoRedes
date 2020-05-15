package controlador;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modelo.CalculadoraIp;
import modelo.ConversorBase;

public class Controlador {

	@FXML
	private TextField txtFieldDireccionIP;
	@FXML
	private ChoiceBox<?> cBoxMascara;
	@FXML
	private TextField txtFieldDireccionRed;
	@FXML
	private TextField txtFieldBroadcast;
	@FXML
	private TextField txtFieldCantidadHosts;
	@FXML
	private TextField txtFieldRangoHosts;
	@FXML
	private TextField txtFieldDireccionIP2;
	@FXML
	private ChoiceBox<?> cBoxMascara2;
	@FXML
	private TextField txtFieldMascara;
	@FXML
	private TextField txtFieldBroadcast2;
	@FXML
	private TextField txtFieldCantidadHosts2;
	@FXML
	private TextField txtFieldBitsRed;
	@FXML
	private TextField txtFieldBitsHosts;
	@FXML
	private TextField txtFieldRangoHosts2;
	@FXML
	private TextField txtFieldEntrada;
	@FXML
	private ChoiceBox<?> cBoxBase;
	@FXML
	private TextField txtFieldDecimal;
	@FXML
	private TextField txtFieldBinario;
	@FXML
	private TextField txtFieldhexa;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize() {
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

			CalculadoraIp calculadora = new CalculadoraIp(ipHost, "", mascaraSimplificada);
			if (calculadora.validarIpHost()) {
				txtFieldDireccionRed.setText(calculadora.getIpRed());
				txtFieldBroadcast.setText(calculadora.getIpBroadcast());
				txtFieldCantidadHosts.setText(calculadora.getCantidadHosts() + "");
				txtFieldRangoHosts.setText(calculadora.getRangoUsable());
			} else {
				mostrarAlerta("IP incorrecta", "La dirección ip ingresada no corresponde a una ip de host");
			}
		} else {
			mostrarAlerta("IP incorrecta", "La dirección ip ingresada no cumple con el formato IPv4");

		}
	}

	public void calcular22() {
		String ipRed = txtFieldDireccionIP2.getText();

		if (validarFormatoIp(ipRed)) {

			int mascaraSimplificada = Integer
					.parseInt(cBoxMascara2.getSelectionModel().getSelectedItem().toString().substring(1));

			CalculadoraIp calculadora = new CalculadoraIp("", ipRed, mascaraSimplificada);

			if (calculadora.validarIpRed()) {

				txtFieldMascara.setText(calculadora.getMascara());
				txtFieldBitsRed.setText(calculadora.getMascaraSimplificada() + "");
				txtFieldBitsHosts.setText(calculadora.getBitsHost() + "");
				txtFieldBroadcast2.setText(calculadora.getIpBroadcast());
				txtFieldCantidadHosts2.setText(calculadora.getCantidadHosts() + "");
				txtFieldRangoHosts2.setText(calculadora.getRangoUsable());

			} else {
				mostrarAlerta("Direccion IP incorrecta",
						"Verifique que la ip sea una dirección de RED y que corresponda con la mascara seleccionada");
			}
		} else {
			mostrarAlerta("IP incorrecta", "La dirección ip ingresada no cumple con el formato IPv4");
		}
	}

	public void convertir() {
		ConversorBase conversor = new ConversorBase(txtFieldEntrada.getText(),
				cBoxBase.getSelectionModel().getSelectedItem().toString());

		String[] resultados;
		try {
			resultados = conversor.convertir();
			txtFieldDecimal.setText(resultados[0]);
			txtFieldBinario.setText(resultados[1]);
			txtFieldhexa.setText(resultados[2]);
		} catch (Exception e) {
			mostrarAlerta("Error numerico", "Verifique que haya ingresado un numero " + e.getMessage());
		}
	}

	private static boolean validarFormatoIp(final String ip) {
		String patron = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
		return ip.matches(patron);
	}

	private void mostrarAlerta(String title, String msg) {
		Alert alert = new Alert(AlertType.WARNING, title, ButtonType.OK);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
