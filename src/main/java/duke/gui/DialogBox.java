package duke.gui;

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

    private static final Color DUKE_TEXT_COLOR = Color.LIGHTGRAY;
    private static final Color USER_TEXT_COLOR = Color.LIGHTBLUE;
    private static CornerRadii TEXT_CORNER_ROUNDING = new CornerRadii(10);
    private static final Insets PADDING = new Insets(10);
    private static final BackgroundFill DUKE_TEXT_BACKGROUND = new BackgroundFill(
            DUKE_TEXT_COLOR,
            TEXT_CORNER_ROUNDING,
            PADDING
    );
    private static final BackgroundFill USER_TEXT_BACKGROUND = new BackgroundFill(
            USER_TEXT_COLOR,
            TEXT_CORNER_ROUNDING,
            PADDING
    );
    private Label text;
    private ImageView displayPicture;


    /**
     * DialogBox constructor.
     *
     * @param label Label for created dialogBox.
     * @param imageView ImageView for created dialogBox.
     * @param isUser true if the dialog is from the user, false if it is from Duke.
     */
    private DialogBox(Label label, ImageView imageView, boolean isUser) {
        text = label;
        displayPicture = imageView;


        text.setBackground(new Background(isUser ? USER_TEXT_BACKGROUND : DUKE_TEXT_BACKGROUND));
        text.setPadding(new Insets(20));
        Circle clip = new Circle(50, 50, 40);
        imageView.setClip(clip);

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

    /**
     * Returns a user DialogBox.
     *
     * @param label
     * @param imageView
     * @return DialogBox originating from user with specified label and image.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView, true);
    }

    /**
     * Returns a Duke DialogBox.
     *
     * @param label
     * @param imageView
     * @return DialogBox originating from Duke with specified label and image.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView, false);
        db.flip();
        return db;
    }
}
