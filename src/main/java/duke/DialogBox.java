package duke;

import java.io.IOException;

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
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

/**
 * Class to create dialog box with user photo and message.
 */
public class DialogBox extends HBox {
    @FXML
    private Label label;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor to initialize Dialog Box.
     *
     * @param text String representation of message.
     * @param iv Image of duke or user.
     * @param i Integer to determine layout of Dialog box.
     */
    public DialogBox(String text, Image iv, int i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayPicture.setImage(iv);
        label.setWrapText(true);
        label.setMinHeight(Region.USE_PREF_SIZE);
        label.setPadding(new Insets(10));
        label.setBackground(new Background(new BackgroundFill(Paint.valueOf("#95d0d0"),
                new CornerRadii(12), null)));
        if (i == 1) {
            label.setBackground(new Background(new BackgroundFill(Paint.valueOf("#1a94a9"),
                    new CornerRadii(12), null)));
            label.setTextFill(Paint.valueOf("fff"));
        }
        label.setText(text);
        setPadding(new Insets(4, 10, 4, 10));
    }

    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Returns a dialog box with appropriate message and photo.
     *
     * @param fullCommand Message of user/duke.
     * @param iv Image of user/duke.
     * @param i Integer to determine position of Imageview.
     * @return DialogBox with appropriate message and photo.
     */
    public static DialogBox getUserDialog(String fullCommand, Image iv, int i) {
        var db = new DialogBox(fullCommand, iv, i);
        if (i == 1) {
            db.flip();
        }
        return db;
    }
}
