package duke.ui.uiController;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

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
        dialog.setPadding(new Insets(10, 10, 10, 10));
        dialog.setStyle("-fx-background-color: #c8d6e5;");

        displayPicture.setImage(img);
        final Circle clip = new Circle(27.5, 27.5, 27.5);
        displayPicture.setClip(clip);
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

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextFill(Color.rgb(19, 15, 64));
        db.dialog.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(7), Insets.EMPTY)));
        db.setSpacing(10.0);
        db.flip();
        return db;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextFill(Color.rgb(83, 92, 104));
        db.dialog.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(7), Insets.EMPTY)));
        db.setSpacing(10.0);
        return db;
    }
}