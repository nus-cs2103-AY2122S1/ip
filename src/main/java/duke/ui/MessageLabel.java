package duke.ui;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MessageLabel extends Label {

    private static final double MAX_WIDTH = 385.0;
    /**
     * Constructor for a MessageLabel.
     * Applies the given Insets to the MessageLabel.
     * Will also load an image from the given file path to add to the MessageLabel.
     *
     * @param msg the given message
     * @param imagePath the given file path to the image
     * @param insets the given Insets
     */

    public MessageLabel(String msg, String imagePath, Insets insets) {
        super(msg);
        this.setMaxWidth(MAX_WIDTH);
        this.setPrefWidth(Region.USE_COMPUTED_SIZE);
        this.setWrapText(true);
        VBox.setMargin(this, insets);

        InputStream iconInput = this.getClass().getResourceAsStream(imagePath);
        Image icon = new Image(iconInput);
        ImageView iconView = new ImageView(icon);
        this.setGraphic(iconView);
    }
}
