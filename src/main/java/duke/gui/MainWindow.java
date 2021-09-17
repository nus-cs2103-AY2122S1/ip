package duke.gui;

import duke.command.Command;
import duke.main.Duke;
import duke.main.DukeException;
import duke.main.Parser;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Initializes the MainWindow
     */
    @FXML
    public void initialize() {
        Stop[] stops = new Stop[] { new Stop(0, Color.DARKVIOLET), new Stop(0.7, Color.DARKBLUE)};
        LinearGradient lgColor = new LinearGradient(
                0,
                0,
                1,
                0,
                true,
                CycleMethod.NO_CYCLE,
                stops);
        BackgroundFill bgFill = new BackgroundFill(lgColor, CornerRadii.EMPTY, Insets.EMPTY);
        dialogContainer.setBackground(new Background(bgFill));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    @FXML
    private void handleUserInput() {
        String userCommand = userInput.getText();
        Label input = new Label(userCommand);
        Label response;
        boolean error = false;
        try {
            Command command = Parser.parse(userCommand);
            String message = command.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
            response = new Label(message);
        } catch (DukeException e) {
            response = new Label(duke.getUi().displayError(e));
            error = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response, error)
        );
        userInput.clear();
    }

    /**
     * Sends the user a greeting message.
     */
    public void greet() {
        Label greeting = new Label(duke.getUi().greet());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, false)
        );
    }
}
