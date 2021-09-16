package banana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;


public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private Image img;
    private String text;

    private DialogBox(String text, Image img) {
        this.text = text;
        this.img = img;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogBox(Font f, Color c) {
        dialog.setFont(f);
        dialog.setTextFill(c);
        dialog.setText(text);
        dialog.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        dialog.setStyle("-fx-background-color : #CBCACA;");
        displayPicture.setImage(img);
    }


    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDialogBox(new Font("American Typewriter",  18),
                Color.SADDLEBROWN);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setDialogBox(new Font("American Typewriter",  14),
                Color.DARKBLUE);
        return db;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }


}
