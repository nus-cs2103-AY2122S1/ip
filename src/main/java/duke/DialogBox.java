package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        //styles
        text.setWrapText(true);
        text.setFont(new Font("Monaco" , 12));

        displayPicture.setFitWidth(80.0);
        displayPicture.setFitHeight(80.0);
        final Circle clip = new Circle(40.0, 40.0, 40.0);
        displayPicture.setClip(clip);

        this.setPadding(new Insets(8));
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setMargin(iv, new Insets(10));
        this.setMargin(l, new Insets(10));
        this.setStyle("-fx-background-radius: 20px, 20px, 20px, 20px;" + "-fx-background-color: #ffffff;" + "-fx-margin: 20px;");

    }

    private void flip() {

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);

        //style
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(8));
        this.setStyle("-fx-background-radius: 20px, 20px, 20px, 20px;" + "-fx-background-color: #e6e6fa;" + "-fx-margin: 20px;");
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}