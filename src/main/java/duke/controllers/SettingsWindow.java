package duke.controllers;

import java.io.IOException;

import duke.exceptions.AuguryException;
import duke.storage.Settings;
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

    private static Settings settings;

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
            s.getScene().getStylesheets().add("/styles/augury_" + SettingsWindow.settings.getTheme() + ".css");

            choiceBox.getItems().addAll("Apollo", "Dark", "Light", "Orca", "Solarised", "Sonicpi", "Tape");
            String currentTheme = SettingsWindow.settings.getTheme().substring(0, 1).toUpperCase()
                    + SettingsWindow.settings.getTheme().substring(1);
            choiceBox.setValue(currentTheme);

            s.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a {@code SettingsWindow} instance.
     */
    public static void showSettingsWindow(Settings settings) {
        SettingsWindow.settings = settings;
        SettingsWindow sw = new SettingsWindow();
    }

    /**
     * Save the Settings.
     */
    @FXML
    private void handleSettingsSave() throws AuguryException {
        String userChoice = choiceBox.getValue().toLowerCase();
        settings.setTheme(userChoice);
        Stage stage = (Stage) choiceBox.getScene().getWindow();
        stage.close();
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
