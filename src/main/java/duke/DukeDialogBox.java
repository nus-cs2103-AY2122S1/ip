package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DukeDialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for the DukeDialogue box
     * @param l label for the dialogue box
     * @param iv image for the dialogue box
     */
    public DukeDialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren()
                .addAll(text,
                        displayPicture);
    }

    public static DukeDialogBox getUserDialog(Label l, ImageView iv) {
        return new DukeDialogBox(l,
                iv);
    }

    public static DukeDialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DukeDialogBox(l,
                iv);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren()
                .setAll(tmp);
    }
}
