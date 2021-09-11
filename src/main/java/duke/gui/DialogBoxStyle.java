package duke.gui;

import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface DialogBoxStyle {
    /**
     * Applies styles to a given ImageView.
     * @param img the ImageView to be styled
     */
    default void styleDisplayPicture(ImageView img) {
        img.setFitWidth(80.0);
        img.setFitHeight(80.0);
        Rectangle clip = new Rectangle(
                img.getFitWidth(), img.getFitHeight()
        );
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);
        img.setClip(null);

        // apply a shadow effect.
        img.setEffect(new DropShadow(10, Color.DARKGRAY));
        img.setImage(image);
    }

    /**
     * Applies User styles to a given text label.
     * @param label The label to be styled.
     */
    default void styleUserTextLabels(Label label) {
        label.setWrapText(true);
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
    }

    /**
     * Applies Duke styles to a given text label.
     * @param label The label to be styled.
     */
    default void styleDukeTextLabels(Label label) {
        label.setWrapText(true);
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Courier",13));
    }
}
