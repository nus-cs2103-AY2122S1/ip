package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    /**
     * Class constructor for DialogBox.
     * @param l The text for this DialogBox.
     */
    private DialogBox(Label l) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(l.getText());
    }

    private void flip(boolean error) {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        if (error) {
            dialog.setTextFill(Color.RED);
        }
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l) {
        return new DialogBox(l);
    }

    public static DialogBox getDukeDialog(Label l, boolean error) {
        var db = new DialogBox(l);
        db.flip(error);
        return db;
    }
}
