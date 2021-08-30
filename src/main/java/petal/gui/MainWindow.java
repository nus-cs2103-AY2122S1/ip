package petal.gui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import petal.Petal;
import petal.components.Responses;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    //FXML components
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    //Reads the sys out
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private Petal petal;

    //Images to be used

    /**
     * Initializes the nodes
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the Petal instance and checks if user has been greeted
     * If not, greets the user
     * @param p Petal instance
     */
    public void setPetal(Petal p) {
        petal = p;
        String startMsg = petal.greetUser();
        dialogContainer.getChildren().addAll(
                PetalDialogBox.getPetalDialog(startMsg));
    }

    /**
     * Sets up the ByetArray/Output stream so that the sysout can be read as a String
     */
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        setUp();

        String input = userInput.getText().trim();
        petal.run(input);
        String output = outputStream.toString().trim();

        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                PetalDialogBox.getPetalDialog(output)
        );

        if (output.equals(Responses.GOODBYE.toString())) {
            Platform.exit();
        }

        userInput.clear();
    }
}
