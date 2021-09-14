package duke.gui;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Color DUKE_COLOR = Color.valueOf("#33383b");
    private static final CornerRadii DUKE_CORNER = new CornerRadii(0, 10, 10, 10, false);
    private static final Background DUKE_BACKGROUND = new Background(new BackgroundFill(DUKE_COLOR, DUKE_CORNER, Insets.EMPTY));

    private static final Color USER_COLOR = Color.valueOf("#000640");
    private static final CornerRadii USER_CORNER = new CornerRadii(10, 0, 10, 10, false);
    private static final Background USER_BACKGROUND = new Background(new BackgroundFill(USER_COLOR, USER_CORNER, Insets.EMPTY));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle avatarIcon;


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
        dialog.setStyle("-fx-text-fill: white");
//        dialog.setPadding(new Insets(0,0,0,20));
        avatarIcon.setFill(new ImagePattern(img));
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
        db.dialog.setBackground(USER_BACKGROUND);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(DUKE_BACKGROUND);

        if (text.contains("ERROR:")) {
            db.dialog.setStyle("-fx-text-fill: #ff0000;");
        }
        return db;
    }
}