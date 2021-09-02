package duke.ui;

import duke.DialogBox;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class GraphicalUi extends Ui {
    private VBox dialogContainer;
    private Image dukeImage;

    public GraphicalUi(VBox dialogContainer, Image dukeImage) {
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
    }

    public void printMessage(String string) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(string, dukeImage)
        );
    }
}
