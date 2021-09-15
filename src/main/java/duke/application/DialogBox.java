package duke.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private final static Color EXCEPTION_COLOR = Color.SALMON;

    /**
     * Generates a dialog box representing communication from one party.
     *
     * @param text Text to be written.
     * @param img Profile image of user.
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

        dialog.setText(text);
        displayPicture.setImage(img);
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
     * Generates dialog box representing input from the user.
     *
     * @param text User input text.
     * @param img User profile image.
     * @return Dialog box representing input from the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generates dialog boxes representing response from Duke.
     *
     * @param text Response text.
     * @param img Duke profile image.
     * @param hasException Whether the message to be shown is for an exception.
     * @return Dialog box representing response from Duke.
     */
    public static ArrayList<DialogBox> getDukeDialog(String text, Image img, boolean hasException) {
        String[] lines = text.split("\n");

        StringBuilder sb = new StringBuilder();
        ArrayList<DialogBox> dialogBoxes = new ArrayList<>();

        // split long responses into blocks of 5 lines each
        for (int i = 0; i < lines.length; i++) {
            sb.append(lines[i]);
            sb.append("\n");

            // pad the continuation message so the text appears on the top of the dialog box
            if (i == lines.length - 1 && i > 4) {
                while (i % 5 != 4) {
                    sb.append("\n");
                    i++;
                }
            }

            // convert the built string into a dialog box if it's the 5th line, or is on the last line
            if (i % 5 == 4 || i == lines.length - 1) {
                DialogBox db = new DialogBox(sb.toString(), img);
                if (hasException) {
                    db.dialog.setTextFill(EXCEPTION_COLOR);
                }
                db.flip();
                dialogBoxes.add(db);
                sb = new StringBuilder();
            }
        }

        return dialogBoxes;
    }
}
