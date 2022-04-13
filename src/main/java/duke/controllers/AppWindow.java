package duke.controllers;

import java.io.IOException;

import duke.App;
import duke.Augury;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for AppWindow. Provides the layout for the other controls.
 */
public class AppWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Augury augury;

    private Image img = new Image(this.getClass().getResourceAsStream("/images/bird.png"));

    private String lastCommand = "";

    /**
     * Main window of the {@code Augury} GUI.
     *
     * @param a Augury instance.
     */
    public AppWindow(Augury a) {
        this.augury = a;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/AppWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            handleKeyboardCommands();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String welcomeMessage = "Hello! How may I help you?";
        dialogContainer.getChildren().add(
            DialogBox.getAuguryDialog(welcomeMessage, img)
        );
    }

    /**
     * Allows {@code scrollPane} to scroll when new messages are added
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = augury.getResponse(input);
        DialogBox auguryDialogBox;

        if (input.trim().equals("")) {
            return;
        } else if (response.startsWith("ERR")) {
            auguryDialogBox = DialogBox.getErrorDialog(response.substring(3), img);
        } else {
            auguryDialogBox = DialogBox.getAuguryDialog(response, img);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                auguryDialogBox
        );
        this.lastCommand = input;
        userInput.clear();

        if (response.equals("The readiness is all.")) {
            Platform.exit();
        }
    }

    /**
     * Creates a dialog box to display information about {@code Augury}.
     * https://code.makery.ch/blog/javafx-dialogs-official/
     */
    @FXML
    private void handleHelpButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Augury");
        alert.setHeaderText(null);
        alert.setContentText(Augury.HELP_MESSAGE);
        alert.getDialogPane().getStylesheets().add("/styles/augury_" + augury.getSettings().getTheme() + ".css");

        alert.showAndWait();
    }

    /**
     * Creates a dialog box to handle settings of {@code Augury}.
     */
    @FXML
    private void handleSettingsButton() {
        SettingsWindow.showSettingsWindow(augury.getSettings());

        scrollPane.getScene().getStylesheets().remove(1);
        scrollPane.getScene().getStylesheets().add("/styles/augury_" + augury.getSettings().getTheme() + ".css");
    }

    private void handleKeyboardCommands() {
        userInput.setOnKeyReleased(event -> {
            String key = event.getCode().toString();
            if (key.equals("UP")) {
                toggleLastCommand();
            }
        });
    }

    private void toggleLastCommand() {
        String input = userInput.getText();
        String temp = lastCommand;

        if (lastCommand.equals("")) {
            return;
        } else if (!input.isBlank()) {
            temp = input;
        }

        userInput.clear();
        userInput.setText(lastCommand);
        userInput.positionCaret(lastCommand.length());
        this.lastCommand = temp;

    }
}
