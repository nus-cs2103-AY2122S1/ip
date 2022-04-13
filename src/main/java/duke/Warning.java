package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Warning class to produce a warning message to user.
 */
public class Warning extends HBox {
    @FXML
    private Label dialogWarning;

    private Warning(String warningMessage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Warning.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogWarning.setText(warningMessage);
    }

    /**
     * Returns a warning in Gui
     *
     * @param warning warning message to be print
     * @return a warning object
     */
    public static Warning getDukeWarning(String warning) {
        return new Warning(warning);
    }
}
