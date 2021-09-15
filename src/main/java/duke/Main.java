package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    private final Image background = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            Image icon = new Image(Main.class.getResourceAsStream("/images/icon.png"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();

            BackgroundImage bi = new BackgroundImage(
                    background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1, true, true, false, true)
            );
            bp.setBackground(new Background(bi));

            Scene scene = new Scene(bp);
            stage.setScene(scene);
            stage.setTitle("Hiko - your best buddy");
            stage.getIcons().add(icon);
            stage.setMinHeight(650);
            stage.setMinWidth(414);

            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
