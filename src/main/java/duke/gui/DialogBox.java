package duke.gui;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Controller class for DialogBox, which represents a message in Duke.
 * Consists of Image and Text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initializes a new DialogBox.
     *
     * @param text Text in the DialogBox.
     * @param textColor Color of the text in the dialog box
     * @param img Image of the DialogBox.
     */
    private DialogBox(String text, Paint textColor, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // General layout of a Dialog Box
        dialog.setText(text);
        dialog.setTextFill(textColor);
        dialog.setWrapText(true);
        dialog.setPadding(new Insets(5));
        displayPicture.setImage(img);
        Circle circle = new Circle(displayPicture.getFitWidth() / 2);
        circle.setCenterX(displayPicture.getFitWidth() / 2);
        circle.setCenterY(displayPicture.getFitWidth() / 2);
        displayPicture.setClip(circle);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10));
        this.setMinHeight(Region.USE_PREF_SIZE);
    }

    private DialogBox(String text, Image img) {
        this(text, Paint.valueOf(Color.TEXT_DEFAULT), img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        this.setAlignment(Pos.TOP_LEFT);
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, boolean isException, Image img) {
        // Set text color to red if it is an Exception
        Paint textColor = isException
                ? Paint.valueOf(Color.TEXT_EXCEPTION)
                : Paint.valueOf(Color.TEXT_DEFAULT);
        var db = new DialogBox(text, textColor, img);

        // Set background color to grey
        Paint dukeBackgroundColor = Paint.valueOf(Color.BACKGROUND_DUKE);
        BackgroundFill dukeBackgroundFill = new BackgroundFill(dukeBackgroundColor, null, null);
        Background dukeBackground = new Background(dukeBackgroundFill);
        db.setBackground(dukeBackground);
        db.flip();
        return db;
    }
}
