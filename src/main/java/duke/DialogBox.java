package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;



public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        final Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        l.setPadding(new Insets(5, 10, 0, 10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #F7FFFF;");
    }
}