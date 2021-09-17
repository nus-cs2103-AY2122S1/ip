package side.gui;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Encapsulates a DialogBox that is used for user and Side messages.
 *
 * @author Lua Yi Da
 */

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        displayPicture.setImage(img);
        this.setImageShape(displayPicture, new Circle(50, 50, 40));

        this.setBackground();
    }

    private void setBackground() {
        BackgroundFill backgroundSettings = new BackgroundFill(Color.ORANGE, new CornerRadii(7),
            new Insets(10, 5, 10, 5));
        Background background = new Background(backgroundSettings);
        this.setBackground(background);
    }

    private void setImageShape(ImageView image, Shape shape) {
        image.setClip(shape);
    }

    /**
     * Creates new DialogBox for user.
     *
     * @param text Text entered.
     * @param img Relevant image of sender.
     * @return DialogBox for message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates new DialogBox for Side.
     *
     * @param text Text to be shown.
     * @param img Relevant image of sender.
     * @return DialogBox for message.
     */
    public static DialogBox getSideDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
