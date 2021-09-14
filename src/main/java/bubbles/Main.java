package bubbles;

import java.io.IOException;

import bubbles.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** A GUI for Bubbles using FXML. */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            fxmlLoader.<MainWindow>getController().setBubbles(new Bubbles());

            stage.setScene(scene);
            stage.setTitle("Bubbles");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
