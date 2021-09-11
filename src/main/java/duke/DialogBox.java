package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.layout.HBox;

/**
 * The DialogBox class encapsulates a dialog box that displays
 * the commands.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class DialogBox extends HBox {

    private Label text;
    private Image displayPicture;

    /**
     * DialogBox constructor that initialises the visual formatting of
     * the dialog box.
     *
     */
    public DialogBox(Label l, Image iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);

        assert displayPicture != null;
        Circle cir = new Circle(0,0,45);
        cir.setStroke(Color.LIGHTCYAN);
        cir.setFill(new ImagePattern(displayPicture));
        cir.setEffect(new DropShadow(+10d, 0d, +2d, Color.SANDYBROWN));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, cir);
        this.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: white;"
                + "-fx-background-image: url(\"/images/background_blue.png\");");


    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        this.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: white;"
                + "-fx-background-image: url(\"/images/background_orange.png\");");
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox with the image on the right.
     *
     * @param l The label (text) to be displayed.
     * @param iv The image to be displayed as a portrait.
     */
    public static DialogBox getUserDialog(Label l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setSpacing(10.0);
        db.setPadding(new Insets(10));
        return db;
    }

    /**
     * Creates a DialogBox with the image on the left.
     *
     * @param l The label (text) to be displayed.
     * @param iv The image to be displayed as a portrait.
     */
    public static DialogBox getDukeDialog(Label l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setSpacing(10.0);
        db.setPadding(new Insets(10));
        return db;
    }
}
