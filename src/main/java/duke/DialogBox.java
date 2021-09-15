package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Ellipse pictureOutline;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        pictureOutline = new Ellipse();
        pictureOutline.setCenterX(50.0);
        pictureOutline.setCenterY(50.0);
        pictureOutline.setRadiusX(50.0);
        pictureOutline.setRadiusY(50.0);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(pictureOutline);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(20);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }
}