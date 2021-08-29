package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {

    private Label text;
    private Image displayPicture;

    public DialogBox(Label l, Image iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);

        Circle cir = new Circle(0,0,45);
        cir.setStroke(Color.LIGHTCYAN);
        cir.setFill(new ImagePattern(displayPicture));
        cir.setEffect(new DropShadow(+10d, 0d, +2d, Color.SANDYBROWN));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, cir);
        this.setBackground(new Background(
                new BackgroundFill(Color.SEASHELL, CornerRadii.EMPTY, new Insets(5))));

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

    public static DialogBox getUserDialog(Label l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setSpacing(10.0);
        db.setPadding(new Insets(10));
        return db;
    }

    public static DialogBox getDukeDialog(Label l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setSpacing(10.0);
        db.setPadding(new Insets(10));
        return db;
    }
}
