package duke.ui.graphical;

import duke.ui.Ui;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class GraphicalUi extends Ui {
    private final VBox dialogContainer;
    private final Image dukeImage;

    /**
     * Initializes a graphical UI instance using JavaFX.
     *
     * @param dialogContainer Dialog container to add messages to.
     * @param dukeImage Image of Duke to associate messages with.
     */
    public GraphicalUi(VBox dialogContainer, Image dukeImage) {
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
    }

    /**
     * Prints a message by adding it as a dialog box to the GUI.
     *
     * @param string Message to print.
     */
    public void printMessage(String string) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(string, dukeImage)
        );
    }
}
