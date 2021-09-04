package duke.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * For the choice box https://coderanch.com/t/649781/java/Set-values-ChoiceBox-created-Scene
 * Creating the stage https://stackoverflow.com/questions/38791933/how-to-define-stage-in-fxml
 */
public class SettingsWindow extends Application {

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    private Stage s = new Stage();

    private SettingsWindow() {
        start(s);
    }

    /**
     * Creates a new {@code SettingsWindow}.
     */
    @Override
    public void start(Stage s) {
        try {
            FXMLLoader fxml = new FXMLLoader(AppWindow.class.getResource("/view/SettingsWindow.fxml"));
            fxml.setController(this);
            s = fxml.load();
            s.setResizable(false);
            s.initModality(Modality.APPLICATION_MODAL);

            choiceBox.getItems().addAll("Light theme", "Dark theme");
            choiceBox.setValue("Light theme");

            s.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a {@code SettingsWindow} instance.
     */
    public static void showSettingsWindow() {
        SettingsWindow sw = new SettingsWindow();
    }

    /**
     * Close the Settings Window.
     */
    @FXML
    private void handleSettingsCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
