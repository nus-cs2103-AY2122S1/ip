package ui.components;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CenterBox extends VBox {

    @FXML
    private Label logo = new Label();
    @FXML
    private Label dialog = new Label();

    /**
     * Constructor for the dialog box
     */
    public CenterBox(String textLogo, String textDialog) {
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
     * Factory method for producing DialogBox
     *
     * @param text the message to be printed in the DialogBox
     * @return DialogBox with message to the left and avatar to the right
     */
    public static CenterBox getCenterBox(String logo, String text) {
        return new CenterBox(logo, text);
    }

}
