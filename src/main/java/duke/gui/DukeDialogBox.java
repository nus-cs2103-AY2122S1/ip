package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class DukeDialogBox extends DialogBox {
    @FXML
    private Label dukeDialog;

    private DukeDialogBox(String text, Image img) {
        super("/view/DukeDialogBox.fxml", img);
        dukeDialog.setText(text);
    }

    public static DialogBox getDialog(String text, Image img) {
        var db = new DukeDialogBox(text, img);
        db.flip();
        return db;
    }
}
