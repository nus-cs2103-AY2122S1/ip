package duke;

import java.io.IOException;

import duke.controllers.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

	private Duke duke = new Duke();
	private Ui ui = new Ui();

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane ap = fxmlLoader.load();
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.setResizable(false);
			fxmlLoader.<MainWindow>getController().setDuke(duke, ui);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
