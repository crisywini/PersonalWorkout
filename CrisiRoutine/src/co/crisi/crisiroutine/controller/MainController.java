package co.crisi.crisiroutine.controller;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import co.crisi.crisiroutine.model.Mode;
import co.crisi.crisiroutine.view.Finder;
import co.crisi.crisitourine.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private BorderPane mainPane;
	@FXML
	private Menu menuFiles;
	private Main main;
	private static Stage primaryStage;
	VBox menuPane;
	MenuPaneController menuController;

	@FXML
	void initialize() {
		loadMenuPane();
	}

	@FXML
	void handleChangeMode(ActionEvent event) {
		String chooseStr = choose("MODOS", "Elije un modo", "BULKING", "SHREDDING");
		if (chooseStr.equals("BULKING")) {
			main.setMode(Mode.BULKING);
			main.generateNew();
		} else {
			main.setMode(Mode.SHREDDING);
			main.generateNew();
		}
		showAlert("Modo elegido: " + chooseStr, "INFORMACIÓN", "", AlertType.INFORMATION);
	}
    @FXML
    void handleAbout(ActionEvent event) {
    	String info = "CrisiRoutine es una aplicación que permite \nGenerar rutinas de entrenamiento leyendo de un archivo los ejercicios\nSe creó con el ánimo de automatizar el proceso de creación de mi rutina personal.";
    	showAlert(info, "INFORMACIÓN", "", AlertType.INFORMATION);
    }

	public void loadMenuPane() {
		if (menuPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader(Finder.class.getResource("MenuPane.fxml"));
				menuPane = (VBox) loader.load();
				menuController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		menuController.setMenuFiles(menuFiles);
		menuController.setMainController(this);
		mainPane.setCenter(menuPane);
	}

	/**
	 * This method has the property of being null if the input is an empty field, so
	 * to verify, verify if is null
	 * 
	 * @param message input
	 * @param title   title of the needed input
	 * @return null if is the cancel button and the input if is the accept button
	 */
	public static String readMessage(String message, String title) {
		String out = "";
		TextInputDialog dialog = new TextInputDialog("");
		dialog.initOwner(primaryStage);
		dialog.setTitle(title);
		dialog.setHeaderText("");
		dialog.setContentText(message);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			out = result.get();
		} else {
			out = null;
		}
		return out;
	}

	public static String choose(String title, String message, String op1, String op2) {
		String out;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);

		alert.setTitle(title);
		alert.setContentText(message);
		alert.setHeaderText("");
		ButtonType buttonTypeOne = new ButtonType(op1);
		ButtonType buttonTypeTwo = new ButtonType(op2);
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne)
			out = op1;
		else
			out = op2;
		return out;
	}

	public static void showAlert(String contentText, String title, String headerText, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.initOwner(primaryStage);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		MainController.primaryStage = primaryStage;
	}
}
