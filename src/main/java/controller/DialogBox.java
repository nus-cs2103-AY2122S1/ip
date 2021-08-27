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

public class DialogBox extends HBox {
    private String userDialogStyle =
            "-fx-background-color: #74D9CA; -fx-padding: 8 16 8 16; -fx-background-radius: 5;";
    private String dukeDialogStyle =
            "-fx-background-color: #F7EFC1; -fx-padding: 8 16 8 16; -fx-background-radius: 5;";
    private Ellipse displayPictureClip = new Ellipse(50, 50, 40, 40);

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

    @FXML
    private void initialize() {
        dialog.setStyle(userDialogStyle);
        displayPicture.setClip(displayPictureClip);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(db.dukeDialogStyle);
        return db;
    }
}
