package nyx.ui;

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

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip(boolean isError) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        setStyle(isError);
    }

    private void setStyle(boolean isError) {
        if (isError) {
            dialog.setStyle("-fx-background-color: #FF0000;"
                    + "-fx-background-radius: 15;"
                    + "-fx-padding: 15;"
                    + "-fx-border-radius: 15;");


        } else {
            dialog.setStyle("-fx-background-color: #0EFFC7;"
                    + "-fx-background-radius: 15;"
                    + "-fx-padding: 15;"
                    + "-fx-border-radius: 15;");
        }
    }

    static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        return db;
    }

    static DialogBox getNyxDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip(false);
        db.setAlignment(Pos.CENTER_LEFT);
        return db;
    }

    static DialogBox getErrorDialog(String error, Image img) {
        DialogBox db = new DialogBox(error, img);
        db.flip(true);
        db.setAlignment(Pos.CENTER_LEFT);
        return db;
    }
}


