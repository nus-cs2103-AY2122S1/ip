package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public Label getDialog() {
        return dialog;
    }

    public ImageView getDisplayPicture() {
        return displayPicture;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        setMargin(tmp.get(0), new Insets(0, 10, 0, 0));
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox object and modifies its CSS style attributes.
     *
     * @param text Text to be contained in DialogBox.
     * @param img Image to be contained in DialogBox.
     * @return DialogBox to display on GUI.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getDialog().setStyle("-fx-background-color: azure; -fx-padding: 5 5 5 5");
        setMargin(db.getDisplayPicture(), new Insets(0, 0, 0, 10));
        db.setPadding(new Insets(10, 0, 10, 25));
        return db;
    }

    /**
     * Creates a DialogBox object and modifies its CSS style attributes.
     * DialogBox's nodes are reversed in positioning.
     *
     * @param text Text to be contained in DialogBox.
     * @param img Image to be contained in DialogBox.
     * @return DialogBox to display on GUI.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getDialog().setStyle("-fx-background-color: azure; -fx-padding: 5 5 5 5");
        db.setPadding(new Insets(10, 10, 10, 0));
        db.flip();
        return db;
    }
}
