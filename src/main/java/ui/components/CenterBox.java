package ui.components;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * VBox for outputting a space background box to the middle of its container.
 * Consist of logo at the top and dialog at the bottom.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class CenterBox extends VBox {

    @FXML
    private Label logo = new Label();
    @FXML
    private Label dialog = new Label();

    /**
     * Constructor for the dialog box.
     * Load the format from fxml and then set the text to Label.
     */
    private CenterBox(String textLogo, String textDialog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CenterBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logo.setText(textLogo);
        dialog.setText(textDialog);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Factory method to generate a CenterBox with specified Strings.
     *
     * @param logo string to be use as logo at the top.
     * @param dialog string of the dialog at the bottom.
     * @return CenterBox with the design according to fxml.
     */
    public static CenterBox getCenterBox(String logo, String dialog) {
        return new CenterBox(logo, dialog);
    }

}
