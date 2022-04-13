package petal.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a Petal Dialogue Box.
 * It has a Label that contains the Petal response to the user input and a
 * circle that is filled with the Petal picture.
 */
public class PetalDialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle profilePicture;
    private final Image petalPicture = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/images/petal.jpg")));

    /**
     * Constructs a PetalDialogBox instance
     *
     * @param text The output
     */
    private PetalDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/PetalDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);

        PictureEditor.setGlow(profilePicture, PictureEditor.COLOR_SAKURA_PINK, 60);
        PictureEditor.setOutline(profilePicture, PictureEditor.COLOR_SAKURA_PINK);

        profilePicture.setFill(new ImagePattern(petalPicture));
    }

    /**
     * Returns a PetalDialogBox instance
     *
     * @param text The output
     * @return The PetalDialogBox instance with the output
     */
    public static PetalDialogBox getPetalDialog(String text) {
        return new PetalDialogBox(text);
    }

}
