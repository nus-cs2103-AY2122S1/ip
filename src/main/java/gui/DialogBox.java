package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private static String USER_BG_COLOR = "ADD8E6";
    private static String DUKE_BG_COLOR = "E8F4F8";

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox
     *
     * @param l label with text for dialog box
     * @param iv image for dialog box
     */
    public DialogBox(Label l, ImageView iv, String colorHex) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(Font.font("Cambria", 16));
        text.setPadding(new Insets(0, 10, 0, 10));

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setStyle(String.format("-fx-background-color: #%s", colorHex));
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
     * Factory method for user dialog box
     *
     * @param l text label
     * @param iv dialog image
     * @return user dialog box
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {

        return new DialogBox(l, iv, DialogBox.USER_BG_COLOR);
    }

    /**
     * Factory method for duke dialog box
     *
     * @param l text label
     * @param iv dialog image
     * @return duke dialog box
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, DialogBox.DUKE_BG_COLOR);
        db.flip();
        return db;
    }
}
