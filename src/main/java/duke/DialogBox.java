package duke;

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

import java.io.IOException;


public class DialogBox extends HBox {
    private Label text;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private static final String FORMAT_RED_TEXT_BUBBLE = "-fx-background-color: ff4d4d; -fx-text-fill: black;";
    private static final String FORMAT_GREEN_TEXT_BUBBLE = "-fx-background-color: #9bf396; -fx-text-fill: black;";
    private static final String FORMAT_WHITE_TEXT_BUBBLE = "-fx-background-color: #FFFFFF; -fx-text-fill: black;";

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
        displayPicture.setClip(new Circle(45, 45, 30));
    }


    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(FORMAT_WHITE_TEXT_BUBBLE);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        if (text.contains("☹ OOPS!!!")) {
            db.dialog.setStyle(FORMAT_RED_TEXT_BUBBLE);
        } else {
            db.dialog.setStyle(FORMAT_GREEN_TEXT_BUBBLE);
        }
        db.flip();
        return db;
    }


}
