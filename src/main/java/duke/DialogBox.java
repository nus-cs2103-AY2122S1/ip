package duke;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

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
        assert img != null : " No Image Provided";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setFont(new Font("Lucida Sans Unicode", 15));
        dialog.setTextFill(Color.BLACK);
        dialog.setPadding(new Insets(10));
        displayPicture.setImage(img);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);
        double xPos = displayPicture.getX() + 50;
        double yPos = displayPicture.getY() + 50;
        displayPicture.setClip(new Circle(xPos, yPos, 50));
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
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE,
                                                    new CornerRadii(10), Insets.EMPTY)));
        db.setSpacing(20.0);
        return db;

    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(10), Insets.EMPTY)));
        db.setSpacing(20.0);
        db.flip();
        return db;
    }
}
