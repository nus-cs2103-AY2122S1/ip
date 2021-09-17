package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import duke.ui.UiMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke(UiMode.GUI);
    private final Image iconImage = new Image(this.getClass().getResourceAsStream("/images/Aegir_icon.png"));
    private final String title = "PATS";

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(641.0);
            stage.setMinWidth(435.0);
            stage.getIcons().add(iconImage);
            stage.setTitle(title);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vbox = fxmlLoader.load();
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            this.duke.initialize(fxmlLoader.getController());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
