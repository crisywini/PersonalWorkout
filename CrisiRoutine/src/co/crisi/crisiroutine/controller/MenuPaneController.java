package co.crisi.crisiroutine.controller;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import co.crisi.crisiroutine.exceptions.NonExistentElementException;
import co.crisi.crisiroutine.model.WeeklyRoutine;
import co.crisi.crisitourine.application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MenuPaneController {

	private MainController mainController;
	private Menu menuFiles;
	private ArrayList<MenuItem> routinesMenus = new ArrayList<MenuItem>();
	private final EventHandler<ActionEvent> HANDLER_MENUS = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			MenuItem menuSelected = (MenuItem) event.getSource();
			String name = menuSelected.getId().toUpperCase();
			try {
				mainController.getMain().setRoutine(name);
				weekTableView.refresh();
			} catch (NonExistentElementException e) {
				MainController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}
		}
	};
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<WeeklyRoutine> weekTableView;

	@FXML
	private TableColumn<WeeklyRoutine, String> day1TableColumn;

	@FXML
	private TableColumn<WeeklyRoutine, String> day2TableColumn;

	@FXML
	private TableColumn<WeeklyRoutine, String> day3TableColumn;

	@FXML
	private TableColumn<WeeklyRoutine, String> day4TableColumn;

	@FXML
	private TableColumn<WeeklyRoutine, String> day5TableColumn;

	@FXML
	void handleGenerateRoutineButton(ActionEvent event) {
		String option = MainController.choose("Guardar", "¿Desea guardar la rutina antes de cambiarla?", "Si", "No");
		if (option.toUpperCase().equals("SI")) {
			String nameRoutine = MainController.readMessage("¿Que nombre desea para la rutina?", "Nombre");
			if (nameRoutine != null) {
				if (!nameRoutine.isEmpty()) {
					TreeMap<String, WeeklyRoutine> weeks = mainController.getMain().getRoutine().getWeeks();
					mainController.getMain().saveRoutine(nameRoutine.toUpperCase(), weeks);
					mainController.getMain().generateNew();
					weekTableView.refresh();
					MainController.showAlert("Rutina guardada con exito\nNueva rutina generada", "Nueva rutina", "",
							AlertType.INFORMATION);
					MenuItem newMenu = new MenuItem(nameRoutine);
					newMenu.setId(nameRoutine.toUpperCase());
					newMenu.setOnAction(HANDLER_MENUS);
					routinesMenus.add(newMenu);
					menuFiles.getItems().clear();
					menuFiles.getItems().addAll(routinesMenus);
				} else {
					MainController.showAlert("Debes agregarle un nombre", "ADVERTENCIA", "", AlertType.WARNING);
				}
			}
		} else {
			mainController.getMain().generateNew();
			weekTableView.refresh();
			MainController.showAlert("Nueva rutina generada", "Nueva rutina", "", AlertType.INFORMATION);
		}

	}

	@FXML
	void handleSaveRoutineButton(ActionEvent event) {
		String nameRoutine = MainController.readMessage("¿Que nombre desea para la rutina?", "Nombre");
		TreeMap<String, WeeklyRoutine> weeks = mainController.getMain().getRoutine().getWeeks();
		if (nameRoutine != null) {
			if (!nameRoutine.isEmpty()) {
				if (mainController.getMain().saveRoutine(nameRoutine.toUpperCase(), weeks)) {
					MainController.showAlert("Rutina guardada con exito", "Rutina guardada", "", AlertType.INFORMATION);
					MenuItem newMenu = new MenuItem(nameRoutine);
					newMenu.setId(nameRoutine);
					newMenu.setOnAction(HANDLER_MENUS);
					routinesMenus.add(newMenu);

					menuFiles.getItems().clear();
					menuFiles.getItems().addAll(routinesMenus);
				} else {
					MainController.showAlert("Parece que ya hay una rutina guardada con ese nombre", "ADVERTENCIA", "",
							AlertType.WARNING);
				}
			} else {
				MainController.showAlert("Debes agregarle un nombre", "ADVERTENCIA", "", AlertType.WARNING);
			}
		}
	}

	@FXML
	void initialize() {
		assert weekTableView != null : "fx:id=\"weekTableView\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert day1TableColumn != null : "fx:id=\"day1TableColumn\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert day2TableColumn != null : "fx:id=\"day2TableColumn\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert day3TableColumn != null : "fx:id=\"day3TableColumn\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert day4TableColumn != null : "fx:id=\"day4TableColumn\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert day5TableColumn != null : "fx:id=\"day5TableColumn\" was not injected: check your FXML file 'MenuPane.fxml'.";
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
		initTableView();
	}

	public void initTableView() {
		day1TableColumn.setCellValueFactory(cellData -> cellData.getValue().day1Info());
		day2TableColumn.setCellValueFactory(cellData -> cellData.getValue().day2Info());
		day3TableColumn.setCellValueFactory(cellData -> cellData.getValue().day3Info());
		day4TableColumn.setCellValueFactory(cellData -> cellData.getValue().day4Info());
		day5TableColumn.setCellValueFactory(cellData -> cellData.getValue().day5Info());
		weekTableView.setItems(Main.WEEKS_DATA);
		weekTableView.refresh();
	}

	public void setMenuFiles(Menu menuFiles) {
		this.menuFiles = menuFiles;
	}

	public TableView<WeeklyRoutine> getWeekTableView() {
		return weekTableView;
	}

}