package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Encapsulates a DialogBox object which consists of a profile picture and text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle circle;

    private DialogBox(String text, int height, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isDuke) {
            circle.setFill(Color.web("#eb5a52"));
            dialog.setAlignment(Pos.CENTER_LEFT);
        }
        dialog.setText(text);
        dialog.setPrefHeight(height);
        dialog.setMinHeight(height);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Counts the number of lines in a string.
     *
     * @param str The string.
     * @return The number of lines.
     */
    public static int countLines(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }

    /**
     * Creates a DialogBox for the user.
     *
     * @param text The text in the dialog box.
     * @return The created DialogBox.
     */
    public static DialogBox getUserDialog(String text) {
        int numOfLines = countLines(text);
        var db = new DialogBox(text, numOfLines * 20 + 20, false);
        return db;
    }

    /**
     * Creates a DialogBox for Duke.
     *
     * @param text The text in the dialog box.
     * @param isError Whether the response is an error message or not.
     * @return The created DialogBox.
     */
    public static DialogBox getDukeDialog(String text, boolean isError) {
        if (isError) {
            text = "ERROR: \n" + text;
        }
        int numOfLines = countLines(text);
        var db = new DialogBox(text, numOfLines * 20 + 20, true);
        db.setPrefHeight(numOfLines * 20 + 20);
        db.getChildren().get(0).setStyle("-fx-background-color: #eb5a52; -fx-background-radius: 10;");
        db.flip();
        return db;
    }
}
