package duke.gui;

import static duke.util.Ui.WELCOME_MESSAGE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Dialog box belonging to Duke.
 */
public class DukeDialogBox extends DialogBox {
    @FXML
    private Label dukeDialog;

    /**
     * Acts as the constructor for the Dialog Box belonging to Duke.
     *
     * @param text The text of the dialog box.
     * @param img The Profile picture of the user saying dialog box.
     */
    private DukeDialogBox(String text, Image img) {
        super("/view/DukeDialogBox.fxml", img);
        dukeDialog.setText(text);
    }

    /**
     * Returns the dialog box facing the appropriate side.
     *
     * @param text The text of the dialog box.
     * @param img The Profile picture of the user saying dialog box.
     * @return The dialog box facing the appropriate side.
     */
    public static DialogBox getDialog(String text, Image img) {
        var db = new DukeDialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Welcomes the user to Duke.
     *
     * @param dialogContainer The dialogContainer to put the dialog in.
     * @param dukeImage The image of Duke.
     */
    public static void welcomeUser(VBox dialogContainer, Image dukeImage) {
        dialogContainer.getChildren().add(DukeDialogBox.getDialog(WELCOME_MESSAGE, dukeImage));
    }
}
