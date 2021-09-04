package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The Ui class encapsulates all the methods for user input and printing to the terminal.
 */
public class Ui {

    private static final Insets OUTPUT_MSG_INSETS = new Insets(10, 100, 0, 10);
    private static final Insets INPUT_MSG_INSETS = new Insets(10, 10, 0, 100);
    private static final String WELCOME_MSG = "Hello! I'm Bob\nWhat can I do for you?";
    private static final String BOB_IMG_PATH = "resources/images/bobthebuilder.jpg";
    private static final String PERSON_IMG_PATH = "resources/images/personicon.png";

    private Image bobImage;
    private Image personImage;

    /**
     * Constructor for a Ui object.
     * Tries to load the images of Bob the Builder and the person icon.
     * If images are not found, set the Images to null.
     */
    public Ui() {
        try {
            FileInputStream bobIconInput = new FileInputStream(BOB_IMG_PATH);
            FileInputStream personIconInput = new FileInputStream(PERSON_IMG_PATH);
            bobImage = new Image(bobIconInput);
            personImage = new Image(personIconInput);
        } catch (FileNotFoundException e) {
            bobImage = null;
            personImage = null;
        }
    }

    /**
     * Creates a Label for a message in the GUI
     *
     * @param msg the given message for the label
     * @return a label representing the message in the GUI.
     */
    private Label createMessageLabel(String msg) {
        Label lbl = new Label(msg);
        lbl.setMaxWidth(385);
        lbl.setPrefWidth(Region.USE_COMPUTED_SIZE);
        lbl.setWrapText(true);
        return lbl;
    }

    /**
     * Creates a Label representing user input
     *
     * @param msg the text of the Label
     * @return a Label with the user icon and input
     */
    public Label inputMsgLabel(String msg) {
        Label lbl = createMessageLabel(msg);
        VBox.setMargin(lbl, INPUT_MSG_INSETS);
        if (personImage != null) {
            ImageView personView = new ImageView(personImage);
            lbl.setGraphic(personView);
        }
        lbl.setContentDisplay(ContentDisplay.RIGHT);
        return lbl;
    }

    /**
     * Creates a Label representing program output
     *
     * @param msg the output of running Duke
     * @return a Label with program output and the Bob the Builder image
     */
    public Label outputMsgLabel(String msg) {
        Label lbl = createMessageLabel(msg);
        VBox.setMargin(lbl, OUTPUT_MSG_INSETS);
        if (bobImage != null) {
            ImageView bobView = new ImageView(bobImage);
            lbl.setGraphic(bobView);
        }
        return lbl;
    }

    /**
     * Creates a Label representing a program error
     *
     * @param msg the message of the error
     * @return a Label with the error message, with no icon
     */
    public Label errorMsgLabel(String msg) {
        Label lbl = createMessageLabel(msg);
        VBox.setMargin(lbl, OUTPUT_MSG_INSETS);
        return lbl;
    }

    /**
     * Returns a JavaFX Label with the welcome message
     * @return a JavaFX Label with the welcome message
     */
    public Label showWelcomeGui() {
        return outputMsgLabel(WELCOME_MSG);
    }
}
