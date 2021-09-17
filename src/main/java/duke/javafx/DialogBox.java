package duke.javafx;

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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().add(buildContent(text, img));
    }

    // @@author Lemonsr
    // Reused from: https://github.com/Lemonsr/ip/blob/master/src/main/java/aisu/ui/gui/DialogBox.java
    // with minor modifications.
    private HBox buildContent(String text, Image img) {
        HBox container = new HBox(5);

        dialog.setText(text);
        dialog.setWrapText(true);
        displayPicture.setFill(new ImagePattern(img));
        displayPicture.setEffect(new DropShadow(+8d, 0d, 0d, Color.GRAY));

        container.setPadding(new Insets(10));

        return container;
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
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        // @@author moreTriangles
        // Reused from: https://github.com/moreTriangles/ip/blob/master/src/main/java/duke/gui/DialogBox.java
        // with minor modifications.
        DialogBox db = new DialogBox(text, img);
        Paint dukeBackgroundColor = Paint.valueOf("#ADD8E6");
        BackgroundFill dukeBackgroundFill = new BackgroundFill(dukeBackgroundColor, null, null);
        Background dukeBackground = new Background(dukeBackgroundFill);
        db.setBackground(dukeBackground);
        db.flip();
        return db;
    }
}
