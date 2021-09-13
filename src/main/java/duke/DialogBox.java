package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogBox extends HBox {
    private Label text;
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
        displayPicture.setClip(new Circle(45, 45, 30));
    }

//    public DialogBox(Label label, ImageView iv) {
//        text = label;
//        displayPicture = iv;
//
//        text.setWrapText(true);
//        text.setPadding(new Insets(20));
//        displayPicture.setFitWidth(150.0);
//        displayPicture.setFitHeight(165.0);
//
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, displayPicture);
//    }



    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

//    public static DialogBox getUserDialog(Label l, ImageView iv) {
//        return new DialogBox(l, iv);
//    }
//
//    public static DialogBox getDukeDialog(Label l, ImageView iv) {
//        var db = new DialogBox(l, iv);
//        db.flip();
//        return db;
//    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        if(text.contains("☹ OOPS!!!")) {
            db.dialog.setStyle("-fx-background-color: ff4d4d; -fx-text-fill: black;");
        } else {
            db.dialog.setStyle("-fx-background-color: #9bf396; -fx-text-fill: black;");
        }
        db.flip();
        return db;
    }


}
