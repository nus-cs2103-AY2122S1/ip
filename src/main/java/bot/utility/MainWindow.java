package bot.utility;

import java.util.Objects;

import bot.commands.Command;
import bot.error.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Ui ui;
    private Parser parser;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/steve.png"))
    );
    private final Image botImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/brain.png"))
    );
    private final Image angryBotImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/angry.png"))
    );

    /**
     * Creates a ScrollPane with the height of the dialog container.
     */
    @FXML
    public void initialize() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.greet(), botImage));
    }

    /**
     * Initializes the Duke Chatbot.
     *
     * @param parser The Parser to be used for evaluating user input.
     */
    public void setUp(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        if (parser.canUnderstand(input)) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, botImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, angryBotImage)
            );
        }
        userInput.clear();
    }

    /**
     * Generate a response to user input.
     */
    protected String getResponse(String input) {
        Command command = parser.parse(input);
        if (command.canEnd()) {
            ui.close();
        }
        String response;
        try {
            response = command.execute();
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }
}
