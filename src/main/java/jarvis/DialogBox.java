package jarvis;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates the dialog box where the profile picture of the responder and the reponse is contained.
     *
     * @param text The response given by the responder
     * @param img The profile picture of the responder
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text); // Set the given text into the dialog box
        dialog.setPadding(new Insets(10, 10, 10, 10));

        boolean isAnErrorMessage =
                dialog.getText().equals(Ui.EMPTY_TODO_DESCRIPTION) ||
                dialog.getText().equals(Ui.EMPTY_DEADLINE_DESCRIPTION) ||
                dialog.getText().equals(Ui.INCOMPLETE_DEADLINE) ||
                dialog.getText().equals(Ui.WRONGLY_FORMATTED_DEADLINE_TIME) ||
                dialog.getText().equals(Ui.EMPTY_EVENT_DESCRIPTION) ||
                dialog.getText().equals(Ui.INCOMPLETE_EVENT_INFO) ||
                dialog.getText().equals(Ui.WRONGLY_FORMATTED_EVENT_TIMINGS) ||
                dialog.getText().equals(Ui.UNRECOGNISED_COMMAND) ||
                dialog.getText().equals(Ui.WRONGLY_FORMATTED_DATE) ||
                dialog.getText().equals(Ui.WRONGLY_FORMATTED_NOTE) ||
                dialog.getText().equals(Ui.INVALID_INDEX) ||
                dialog.getText().equals(Ui.EMPTY_TASK_DELETE) ||
                dialog.getText().equals(Ui.EMPTY_NOTE_DELETE) ||
                dialog.getText().equals(Ui.EMPTY_TASK_DONE) ||
                dialog.getText().equals(Ui.INVALID_DATE_OT_TIMING) ||
                dialog.getText().equals(Ui.START_IS_AFTER_END) ||
                dialog.getText().equals(Ui.MISSING_SEARCH_PHRASE) ||
                dialog.getText().equals(Ui.EMPTY_NOTE_TITLE);
        
        if (isAnErrorMessage) {
            //Error message to be displayed in brown to alert user
            dialog.setStyle("-fx-text-fill: CRIMSON; -fx-background-color: LIGHTBLUE;");
        } else {
            dialog.setStyle("-fx-background-color: LIGHTBLUE;");
        }

        displayPicture.setImage(img); // Set the given image into the dialog box and display it

        if (dialog.getText().equals(Ui.BYE)) {
            Main.end();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a new dialog box containing the user's response and profile picture.
     *
     * @param text Response of the user
     * @param img Profile picture of the user
     * @return The dialog box containing the user's response and profile picture
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box containing the Jarvis' response and profile picture.
     *
     * @param text Response of Jarvis
     * @param img Profile picture of Jarvis
     * @return The dialog box containing the Jarvis' response and profile picture
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // Flips Jarvis' dialog box such that its response is on the left of the user's response
        return db;
    }
}
