package co.crisi.crisitourine.application;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import co.crisi.crisiroutine.controller.MainController;
import co.crisi.crisiroutine.exceptions.NonExistentElementException;
import co.crisi.crisiroutine.model.Mode;
import co.crisi.crisiroutine.model.Routine;
import co.crisi.crisiroutine.model.WeeklyRoutine;
import co.crisi.crisiroutine.persistence.Persistence;
import co.crisi.crisiroutine.view.Finder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements ICrisiRoutineControl {

	private static Routine routine;
	public static ObservableList<WeeklyRoutine> WEEKS_DATA = FXCollections.observableArrayList();
	final EventHandler<WindowEvent> closer = new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			String option = MainController.choose("Guardar", "¿Deseas guardar los datos?", "Si", "No");
			if (option.toUpperCase().equals("SI")) {
				try {
					saveData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	};

	@Override
	public void start(Stage primaryStage) {
		loadData();
		loadMain(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void loadMain(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader(Finder.class.getResource("MainView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			controller.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(closer);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		Main.routine = routine;
	}

	public static void saveData() throws IOException {
		Persistence.serialize(routine);
	}

	public void loadData() {
		File file = new File(Persistence.ROUTINE_PATH);
		if (file.exists()) {
			try {
				Routine routine = (Routine) Persistence.deserialize();
				setRoutine(routine);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			String initial = MainController.choose("Inicio",
					"Parece que no hay rutinas aún\nTe gustaría añadir un plan de entrenamiento?", "Si", "No");
			if (initial.toUpperCase().equals("SI")) {
				String plain = MainController.choose("Inicio", "Elije un plan", "BULKING", "SHREDDING");
				if (plain.equals("BULKING")) {
					setRoutine(new Routine(Mode.BULKING));
				} else {
					setRoutine(new Routine(Mode.SHREDDING));
				}
			} else {
				MainController.showAlert("Hasta luego", "BYE", "Saliendo de Crisi routine...", AlertType.INFORMATION);
				System.exit(0);
			}
		}
		WEEKS_DATA.addAll(routine.getWeeklyRoutineInList());
	}

	@Override
	public void generateNew() {
		WEEKS_DATA.clear();
		routine.generateNew();
		WEEKS_DATA.addAll(routine.getWeeklyRoutineInList());
	}

	@Override
	public void setMode(Mode mode) {
		routine.setMode(mode);
	}

	@Override
	public boolean saveRoutine(String name, TreeMap<String, WeeklyRoutine> weeks) {
		return routine.saveRoutine(name, weeks);
	}

	@Override
	public void setRoutine(String name) throws NonExistentElementException {
		WEEKS_DATA.clear();
		routine.setRoutine(name);
		WEEKS_DATA.addAll(routine.getWeeklyRoutineInList());

	}

}
