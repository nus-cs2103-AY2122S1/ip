package controller;

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
import javafx.scene.shape.Ellipse;

/**
 * Encapsulates a controller used to control the dialog box user interface.
 * Includes an `ImageView` and `Label`.
 */
public class DialogBox extends HBox {
    private String userDialogStyle =
            "-fx-text-fill: #FFFFFF; -fx-background-color: linear-gradient(to bottom right, #7500AC, #BE1499);";
    private String dukeDialogStyle =
            "-fx-background-color: #F2F2F2;";
    private Ellipse displayPictureClip = new Ellipse(30, 25, 25, 25);

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    @FXML
    private void initialize() {
        displayPicture.setClip(displayPictureClip);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a dialog box styled for the user.
     *
     * @param text Text from user.
     * @param img Image of user.
     * @return `DialogBox`.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setStyle(db.userDialogStyle);
        return db;
    }

    /**
     * Gets a dialog box styled for duke.
     *
     * @param text Text from duke.
     * @param img Image of duke.
     * @return `DialogBox`.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(db.dukeDialogStyle);
        return db;
    }
}
