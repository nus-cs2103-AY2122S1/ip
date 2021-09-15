package brobot.customcontrol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;
    private final Circle clip = new Circle(35, 35, 35);


    /**
     * DialogBox Constructor
     * @param l The label for the dialogBox
     * @param iv The image for the dialogBox
     */
    public DialogBox(Label l, ImageView iv, int translateXValue) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setPadding(new Insets(5, 10, 5, 10));
        text.setTranslateX(translateXValue);
        //Credits: https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.Label&method=setBackground
        text.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(204, 204, 204, 0.7),
                        new CornerRadii(5.0),
                        new Insets(-1.0))));
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10, 10, 10, 10));
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
        return new DialogBox(l, iv, -10);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, 10);
        db.flip();
        return db;
    }
}
