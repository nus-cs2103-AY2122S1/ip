package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    /**
     * Constructor for DialogBox
     *
     * @param label text to display
     * @param imageView image of the user
     */
    private DialogBox(Label label, ImageView imageView) {
        label.setWrapText(true);
        label.setPadding(new Insets(0, 5, 0, 5));

        imageView.setFitWidth(100.0);
        imageView.setFitHeight(100.0);
        imageView.setClip(new Circle(50, 50, 50));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label, imageView);
        this.setPadding(new Insets(0, 0, 5, 0));
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
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
