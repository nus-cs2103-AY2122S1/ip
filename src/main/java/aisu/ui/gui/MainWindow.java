package aisu.ui.gui;

import aisu.Aisu;
import aisu.command.WelcomeCommand;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */

public class MainWindow extends AnchorPane {
    // The @FXML annotation marks a private or protected member and makes it accessible to FXML despite its modifier.
    // Without the annotation, we will have to make everything public and expose our UI to unwanted changes.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Aisu aisu;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.png"));
    private final Image aisuImage = new Image(this.getClass().getResourceAsStream("/images/bot.gif"));

    /**
     * Initialises Storage and TaskList used by Duke. Shows welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Set Aisu to an aisu object.
     * @param a The Aisu object.
     */
    public void setAisu(Aisu a) {
        aisu = a;
    }

    /**
     * Shows welcome message when Aisu application starts.
     */
    @FXML
    private void showWelcome() {
        WelcomeCommand welcome = new WelcomeCommand();
        welcome.execute();
        makeAisuSay(welcome.showUiText());
    }

    /**
     * Commands Aisu to display a text.
     * @param output The text Aisu says.
     */
    private void makeAisuSay(String output) {
        dialogContainer.getChildren().addAll(DialogBox.getAisuDialog(output, aisuImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean isExit = false;
        String input = userInput.getText();
        String response = aisu.getResponse(input);
        if (response.equals("See you next time! :D")) {
            isExit = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAisuDialog(response, aisuImage)
        );
        userInput.clear();

        // @@author CheyanneSim-reused
        if (isExit) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
