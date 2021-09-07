package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TestDialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a new JavaFX dialog box.
     *
     * @param l Label for the dialog box.
     * @param iv The display picture for the JavaFX.
     */
    public TestDialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static TestDialogBox getUserDialog(Label l, ImageView iv) {
        return new TestDialogBox(l, iv);
    }

    public static TestDialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new TestDialogBox(l, iv);
        db.flip();
        return db;
    }
}
