package iris;

import java.util.Timer;

import iris.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class MainWindow extends AnchorPane {

    private final Ui ui;
    private final ExitProgram exitProgram;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Iris iris;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image irisImage = new Image(this.getClass().getResourceAsStream("/images/Iris.png"));

    private final int seconds;

    /**
     * Constructor for MainWindow
     */
    public MainWindow() {
        ui = new Ui();
        seconds = 2;
        exitProgram = new ExitProgram();
    }

    /**
     * Method called upon starting of Iris.
     * Sets height of scroll panel and
     * sends greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getIrisDialog(ui.guiGreet(), irisImage)
        );
    }

    /**
     * Sets Iris to new Iris.
     *
     * @param iris The new Iris to be set.
     */
    public void setIris(Iris iris) {
        this.iris = iris;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the
     * other containing Iris's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = iris.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIrisDialog(response, irisImage)
        );
        if (response.equals("Bye. Hope to see you again soon!")) {
            new Timer().schedule(exitProgram, seconds * 1000);
        }
        userInput.clear();
    }
}
