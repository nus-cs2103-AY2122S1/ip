import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Background ERROR_BACKGROUND = new Background(
            new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY));

    private static final Background USER_BACKGROUND = new Background(
            new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY));

    private static final Font USER_FONT = Font.font("Arial", FontWeight.BOLD, 14);

    private static final Font DUKE_FONT = new Font("Arial", 14);

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        cropDisplayPicture(img);
    }

    private void cropDisplayPicture(Image img) {
        boolean hasSmallerWidth = img.getWidth() < img.getHeight();
        double newMeasure = hasSmallerWidth ? img.getWidth() : img.getHeight();
        double x = (img.getWidth() - newMeasure) / 2;
        double y = (img.getHeight() - newMeasure) / 2;

        Rectangle2D rect = new Rectangle2D(x, y, newMeasure, newMeasure);
        displayPicture.setViewport(rect);
        displayPicture.setSmooth(true);
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

    /**
     * A method to set styles for user's dialog box.
     */
    private void setUserStyle() {
        setBackground(USER_BACKGROUND);
        dialog.setFont(USER_FONT);
    }

    /**
     * A method to set styles for duke's dialog box.
     *
     * @param isError Boolean indicating whether dialog displays an error.
     */
    private void setDukeStyle(boolean isError) {
        if (isError) {
            setBackground(ERROR_BACKGROUND);
        }
        dialog.setFont(DUKE_FONT);
    }

    /**
     * Formats the user input into a dialog box.
     *
     * @param text The user input.
     * @param img The user profile image.
     * @return DialogBox containing the user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setUserStyle();
        return db;
    }

    /**
     * Formats the response to user input into a dialog box.
     *
     * @param text The response.
     * @param img The profile image for Duke.
     * @param isError Boolean indicating whether error dialog should be shown.
     * @return DialogBox containing the response from Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.setDukeStyle(isError);
        db.flip();
        return db;
    }
}
