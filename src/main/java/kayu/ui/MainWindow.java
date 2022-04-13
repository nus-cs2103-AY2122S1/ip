package kayu.ui;

import static kayu.KayuInterface.ASSERT_FAIL_ABSENT_IMAGE;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kayu.Kayu;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    // Image paths (starting from /src/main/java/resources).
    private static final String KAYU_IMAGE_PATH = "/images/duke.png";
    private static final String USER_IMAGE_PATH = "/images/user.png";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Kayu kayu;
    private Image userImage;
    private Image kayuImage;

    /**
     * Initializes the UI window for user to interact with.
     */
    @FXML
    public void initialize() {
        setImages();
        fixWindowHeight();
        initialiseKayu();
    }

    private void fixWindowHeight() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void setImages() {
        InputStream kayuImageStream = this.getClass().getResourceAsStream(KAYU_IMAGE_PATH);
        InputStream userImageStream = this.getClass().getResourceAsStream(USER_IMAGE_PATH);

        assert (kayuImageStream != null) : String.format(ASSERT_FAIL_ABSENT_IMAGE, KAYU_IMAGE_PATH);
        assert (userImageStream != null) : String.format(ASSERT_FAIL_ABSENT_IMAGE, USER_IMAGE_PATH);

        kayuImage = new Image(kayuImageStream);
        userImage = new Image(userImageStream);
    }

    /**
     * Initializes the Kayu program.
     */
    private void initialiseKayu() {
        kayu = new Kayu();
        kayu.initialize();
        createKayuDialog(kayu.getGreeting());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kayu.executeAndRespond(input);

        createUserDialog(input);
        createKayuDialog(response);

        if (kayu.isRecentCommandBye()) {
            kayu.exit();
        }
    }

    private void createUserDialog(String input) {
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(userDialog);
        userInput.clear();
    }

    private void createKayuDialog(String response) {
        DialogBox kayuDialog = DialogBox.getKayuDialog(response, kayuImage);
        dialogContainer.getChildren().add(kayuDialog);
    }
}
