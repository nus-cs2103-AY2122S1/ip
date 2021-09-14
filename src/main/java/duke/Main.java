package duke;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private StackPane stackPane;
    private Duke duke = new Duke("data/duke.txt");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow(stage);
        Scene scene = new Scene(mw);
        stage.setScene(scene);
        stage.setTitle("Duke");
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        mw.setDuke(duke);
        mw.start();
        stage.show();
    }
}