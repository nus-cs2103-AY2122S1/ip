package pika.ui;

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
import javafx.scene.shape.Circle;

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

        displayPicture.setClip(new Circle(40, 38, 38));
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void setPikaStyle() {
        dialog.setStyle("-fx-background-color: #ffd700;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;");
    }

    private void setErrorStyle() {
        dialog.setStyle("-fx-background-color: #ff3300;"
                + "-fx-background-radius: 15;"
                + "-fx-padding: 15;");
    }

    private void setUserStyle() {
        dialog.setStyle("-fx-background-color: #ffefd5;"
                + "-fx-background-radius: 15;"
                + "-fx-padding: 15;");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.setUserStyle();
        return db;
    }

    public static DialogBox getPikaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_LEFT);
        db.setPikaStyle();
        db.flip();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_LEFT);
        db.setErrorStyle();
        db.flip();
        return db;
    }
}
