package hyddd.uimanager;

import hyddd.main.Hyddd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @@author Hang Zelin
 *
 * Controller for MainWindow. Provides the layout for the other controls.
 * Note: All images, including user's profile, hyddd's profile, background image
 * are free to use. They are available in https://www.zhihu.com/, where people
 * are happy to share nice images with one another.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private boolean isEnded = false;
    private Rectangle emptySpace;
    private Hyddd hyddd;
    private BackgroundImage backgroundImage;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image hydddImage = new Image(this.getClass().getResourceAsStream("/images/Hyddd.jpg"));


    //@@author Hang Zelin-reused
    //Reused from https://edencoding.com/scene-background/
    // with minor modifications
    private void setBackgroundImage() {
        BackgroundSize backgroundSize;

        backgroundSize = new BackgroundSize(400, 600,
                true, true, true, true);
        backgroundImage = new BackgroundImage(new Image("/background/background.jpg"),
                null, null, null, backgroundSize);
    }

    private Rectangle setEmptySpace() {
        //Set empty space between dialogs.
        emptySpace = new Rectangle(400, 40);
        emptySpace.setFill(Color.TRANSPARENT);
        return emptySpace;
    }

    /**
     * Initialize DialogBox and MainWindow stuff.
     */
    @FXML
    public void initialize() {
        //Set scrollPane
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //Set background
        this.setBackgroundImage();
        dialogContainer.setBackground(new Background(backgroundImage));
    }

    /**
     * Constructor for MainWindow, initialize with a helloMessage.
     *
     * @param h hyddd to set ui position.
     */
    public void setHyddd(Hyddd h) {
        hyddd = h;
        TextUi textUi = new TextUi();
        String response = textUi.helloMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getHydddDialog(response, hydddImage)
        );
    }

    private boolean checkIfEnded(String input) {
        boolean isEnded;
        isEnded = input.contains("bye");
        return isEnded;
    }


    private void checkIfQuit() {
        //Successfully exit the application
        if (isEnded) {
            System.exit(0);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing hyddd's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hyddd.getResponse(input);

        checkIfQuit();
        dialogContainer.getChildren().addAll(
                setEmptySpace(),
                DialogBox.getUserDialog(input, userImage),
                setEmptySpace(),
                DialogBox.getHydddDialog(response, hydddImage)
        );
        isEnded = checkIfEnded(input);
        userInput.clear();
    }

}
