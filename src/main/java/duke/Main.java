package duke;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDuke(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("DUKE!");
        String css = getClass().getResource("/view/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        //scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
