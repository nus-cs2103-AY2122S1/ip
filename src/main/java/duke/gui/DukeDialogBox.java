package duke.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DukeDialogBox extends HBox implements DialogBoxStyle {

    private Label text;
    private ImageView displayPicture;

    /**
     * A constructor for a dialog box that represents Duke's output messages.
     * @param l The Label component for the message
     * @param iv The ImageView containing Duke's picture
     */
    public DukeDialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        styleDukeTextLabels(text);
        styleDisplayPicture(displayPicture);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, text);
    }
}