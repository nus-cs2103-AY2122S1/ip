package duke;

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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private static BackgroundFill DUKE_BACKGROUND = new BackgroundFill(
            Color.web("#d6d6d6"), CornerRadii.EMPTY, Insets.EMPTY);

    private static BackgroundFill USER_BACKGROUND = new BackgroundFill(
            Color.web("#e3e1d5"), CornerRadii.EMPTY, Insets.EMPTY);

    public DialogBox(Label label, ImageView iv, boolean isUser) {
        text = label;
        displayPicture = iv;

        text.setWrapText(true);
        if (isUser) {
            displayPicture.setFitHeight(50.0);
            displayPicture.setFitWidth(50.0);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFont(new Font("Lucida Console", 10));
            this.setBackground(new Background(USER_BACKGROUND));
        } else {
            displayPicture.setFitWidth(100.0);
            displayPicture.setFitHeight(100.0);
            this.setBackground(new Background(DUKE_BACKGROUND));
        }

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Taken from https://se-education.org/guides/tutorials/javaFxPart3.html
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, true);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        return db;
    }
}
