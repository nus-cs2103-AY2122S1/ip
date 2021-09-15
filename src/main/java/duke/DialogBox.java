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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double USER_DIALOG_WIDTH = 140;
    private static final double DUKE_DIALOG_WIDTH = 280;

    @FXML
    private HBox textBox;
    @FXML
    private Text dialog;
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

        BackgroundFill bf = new BackgroundFill(
                Color.LIGHTSKYBLUE,
                new CornerRadii(25, 0, 25, 25, false),
                new Insets(10)
        );
        textBox.setBackground(new Background(bf));

        final double textDisplayWidth = text.length() * 7;
        dialog.setText(text);
        dialog.setWrappingWidth(Math.min(USER_DIALOG_WIDTH, textDisplayWidth));

        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        BackgroundFill bf = new BackgroundFill(
                Color.BLANCHEDALMOND,
                new CornerRadii(0, 10, 10, 10, false),
                new Insets(5)
        );
        textBox.setBackground(new Background(bf));
        textBox.setPadding(new Insets(15));

        dialog.setFont(new Font("Consolas", 14));
        dialog.setWrappingWidth(DUKE_DIALOG_WIDTH);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
