package pats;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pats.ui.MainWindow;
import pats.ui.UiMode;

/**
 * A GUI for Pats using FXML.
 */
public class Main extends Application {

    private Pats pats = new Pats(UiMode.GUI);
    private final Image iconImage = new Image(this.getClass().getResourceAsStream("/images/aegir_icon.png"));
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
            fxmlLoader.<MainWindow>getController().setDuke(pats);
            this.pats.initialize(fxmlLoader.getController());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
