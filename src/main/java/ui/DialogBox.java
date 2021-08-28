package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * DialogBox Ui component. Referenced from https://se-education.org/guides/tutorials/javaFx.html
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setPadding(new Insets(10));
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox userDialog = new DialogBox(l, iv);
        userDialog.setStyle("-fx-background-color: #00FFFF");
        userDialog.setAlignment(Pos.CENTER_RIGHT);
        return userDialog;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox dukeDialog = new DialogBox(l, iv);
        dukeDialog.flip();
        dukeDialog.setStyle("-fx-background-color: #7FFFD4");
        dukeDialog.setAlignment(Pos.CENTER_LEFT);
        return dukeDialog;
    }
}
