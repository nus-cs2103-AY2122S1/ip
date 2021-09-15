package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * The Dialog box belonging to the User.
 */
public class UserDialogBox extends DialogBox {
    @FXML
    private Label userDialog;

    /**
     * Represents the constructor for the Dialog Box belonging to the User.
     *
     * @param text The text of the dialog box.
     * @param img The Profile picture of the user saying dialog box.
     */
    private UserDialogBox(String text, Image img) {
        super("/view/UserDialogBox.fxml", img);
        userDialog.setText(text);
    }

    /**
     * Returns the dialog box facing the appropriate side.
     *
     * @param text The text of the dialog box.
     * @param img The Profile picture of the user saying dialog box.
     * @return The dialog box facing the appropriate side.
     */
    public static DialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
