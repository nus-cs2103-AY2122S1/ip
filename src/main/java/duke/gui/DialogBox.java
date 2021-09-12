package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Region;

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

    @FXML
    public void initialize() {
        Circle clip = new Circle(30, 30, 30);
        this.displayPicture.setClip(clip);
    }

    private void setDialog(String text) {
        this.dialog.setText(text);
    }

    private void setImage(Image img) {
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private static HBox flip(HBox db) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(db.getChildren());
        Collections.reverse(tmp);
        db.getChildren().setAll(tmp);
        return db;
    }

    public static HBox getUserDialog(String text, Image img) {
        HBox db = DialogBox.getDialog(text, img);
        ObservableList<Node> tmp = FXCollections.observableArrayList(db.getChildren());
        tmp.forEach(child -> {
                if (child instanceof Label) {
                    ((Label) child).setStyle("-fx-background-color: #90ee90; -fx-background-radius: 5");
                }
        });
        return db;
    }

    public static HBox getDukeDialog(String text, Image img, boolean isExceptionReply) {
        HBox db = DialogBox.getDialog(text, img);
        db = DialogBox.flip(db);
        if (isExceptionReply) {
            ObservableList<Node> tmp = FXCollections.observableArrayList(db.getChildren());
            tmp.forEach(child -> {
                    if (child instanceof Label) {
                        ((Label) child).setStyle("-fx-background-color: #fa8072; -fx-background-radius: 5");
                    }
            });
        }
        return db;
    }

    private static HBox getDialog(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            HBox db = fxmlLoader.load();
            fxmlLoader.<DialogBox>getController().setDialog(text);
            fxmlLoader.<DialogBox>getController().setImage(img);
            return db;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
