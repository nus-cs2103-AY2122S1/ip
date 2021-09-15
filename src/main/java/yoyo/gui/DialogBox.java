package yoyo.gui;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Control Class for Dialog Box of JavaFX GUI.
 */
public class DialogBox extends HBox {
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

        dialog.setText(text);
        dialog.setFont(Font.font ("serif", 14));
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
    }

    /**
     * Factory method for creating user's dialog box.
     *
     * @param text Text to be shown.
     * @param img  Image for user.
     * @return A DialogBox instance.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Color.rgb(160, 185, 198),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return db;
    }

    /**
     * Factory method for creating Yoyo's dialog box.
     *
     * @param text Text to be shown.
     * @param img  Image for Yoyo.
     * @return A DialogBox instance.
     */
    public static DialogBox getYoyoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.rgb(165, 248, 211),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return db;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
