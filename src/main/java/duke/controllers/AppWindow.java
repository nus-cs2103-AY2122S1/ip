package duke.controllers;

import java.io.IOException;

import duke.App;
import duke.Augury;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Augury a) {
        augury = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = augury.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getAuguryDialog(response)
        );
        userInput.clear();
    }
}
