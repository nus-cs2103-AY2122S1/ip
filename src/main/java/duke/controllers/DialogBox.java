package duke.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private HBox dialogContainer;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AppWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setMinHeight(Label.USE_PREF_SIZE); // stops message from truncating
        dialog.getStyleClass().add("dialog");
        dialog.setText(text);
    }

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AppWindow.class.getResource("/view/ImageDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setMinHeight(Label.USE_PREF_SIZE); // stops message from truncating
        dialog.setText(text);
        dialog.getStyleClass().add("dialog");
        dialog.getStyleClass().add("dialog--augury");
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String text) {
        DialogBox userDialog = new DialogBox(text);
        return userDialog;
    }

    public static DialogBox getAuguryDialog(String text, Image img) {
        DialogBox auguryDialog = new DialogBox(text, img);
        auguryDialog.flip();
        return auguryDialog;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox auguryDialog = new DialogBox(text, img);
        auguryDialog.getStyleClass().add("dialog--error");
        auguryDialog.flip();
        return auguryDialog;
    }
}
