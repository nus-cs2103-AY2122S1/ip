package energy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import energy.controller.MainWindow;

/**
 * A GUI for Energy using FXML.
 */
public class Main extends Application {

    private final Energy energy = new Energy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Image energyImage =
                    new Image(getClass().getResourceAsStream("/images/GivePLZTransparent.png"));
            fxmlLoader.setRoot(new MainWindow());
            stage = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setEnergy(energy);
            stage.getIcons().add(energyImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
