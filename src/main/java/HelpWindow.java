import java.io.IOException;
import java.nio.file.Files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpWindow extends AnchorPane {
    public static final String HELP_DOC = "app_data/helpDocumentation.txt";
    @FXML
    private Stage window;
    @FXML
    private Text helpText;

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        try {
            window = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HelpWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            window.setScene(scene);
            window.setTitle("Help Documentation");
        } catch (IOException e) {
            e.printStackTrace();
        }
        helpText.setText(parseHelpDoc());
    }

    private String parseHelpDoc() {
        String result = "";
        try {
            java.nio.file.Path filePath = java.nio.file.Paths.get(HELP_DOC);
            result = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Opens the help window.
     */
    public void show() {
        window.show();
    }

    /**
     * Check if the help window is opened.
     *
     * @return true if the help window has been opened, false otherwise
     */
    public boolean isShowing() {
        return window.isShowing();
    }

    /**
     * Focuses on the opened help window.
     */
    public void focus() {
        window.requestFocus();
    }

    /**
     * Hides the opened help window.
     */
    public void hide() {
        window.hide();
    }

}
