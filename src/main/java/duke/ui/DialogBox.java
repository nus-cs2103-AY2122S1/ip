package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The dialog box component for chat.
 */
public class DialogBox extends HBox {

    private Label text;
    private Image displayPicture;

    /**
     * Creates a new dialog box ui that contains the message in l and the profile picture in iv.
     * @param l the message to be displayed
     * @param iv the profile picture to be displayed
     */
    public DialogBox(Label l, Image iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        Circle imgCircle = new Circle(30);
        imgCircle.setFill(new ImagePattern(iv));
        this.setSpacing(20);
        this.setStyle("-fx-background-color: linear-gradient(to right, silver, thistle);");
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, imgCircle);
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

    public static DialogBox getUserDialog(Label l, Image i) {
        return new DialogBox(l, i);
    }

    public static DialogBox getDukeDialog(Label l, Image i) {
        var db = new DialogBox(l, i);
        db.flip();
        return db;
    }
}
