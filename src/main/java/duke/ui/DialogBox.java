package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        //styles
        text.setWrapText(true);

        //image styles
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        final Circle clip = new Circle(50.0, 50.0, 50.0); //if cannot add padding just change radius to 40
        displayPicture.setClip(clip);

        //DialogBox styles
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(5)); //TODO
        this.setMargin(iv, new Insets(5));
        this.setMargin(l, new Insets(5));
        this.getChildren().addAll(text, displayPicture);
        this.setStyle("-fx-background-radius: 20px, 20px, 20px, 20px;" + "-fx-background-color: #ffffff;" + "-fx-margin: 20px;");
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);

        //styles
        this.setAlignment(Pos.CENTER_LEFT);
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