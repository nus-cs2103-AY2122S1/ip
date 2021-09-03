package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class UserDialogBox extends DialogBox {
    @FXML
    private Label userDialog;

    private UserDialogBox(String text, Image img) {
        super("/view/UserDialogBox.fxml", img);
        userDialog.setText(text);
    }

    public static DialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
