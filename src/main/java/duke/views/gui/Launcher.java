package duke.views.gui;

import java.io.IOException;

import duke.views.gui.controllers.MainWindow;
import duke.views.strategies.Parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Gui gui;

    @Override
    public void start(Stage stage) {
        try {
            this.gui = new Gui(new Parser());
            this.gui.initCallbacks();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGui(this.gui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        gui.endCallbacks();
    }
}
