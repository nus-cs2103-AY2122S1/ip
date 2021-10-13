package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This class encapsulates a box to contain the dialog between Duke and the user.
 *
 * @author Kleon Ang
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox.
     *
     * @param text The Label containing the dialog text.
     */
    private DialogBox(String text, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader;
            if (isDuke) {
                fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/DukeDialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/UserDialogBox.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    /**
     * Gets a DialogBox containing user dialog.
     *
     * @param text The string containing the user dialog.
     * @return A DialogBox with the user dialog.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, false);
    }

    /**
     * Gets a DialogBox containing Duke's dialog.
     *
     * @param text The string containing Duke's dialog.
     * @return A DialogBox with Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, true);
    }
}
