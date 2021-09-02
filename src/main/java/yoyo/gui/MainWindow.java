package yoyo.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yoyo.Yoyo;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Yoyo yoyo;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image yoyoImage = new Image(this.getClass().getResourceAsStream("/images/Yoyo.jpeg"));

    /**
     * Constructor class for MainWindow component.
     */
    public MainWindow(Yoyo yoyo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.yoyo = yoyo;
    }


    /**
     * Actions to be executed when starting up GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getYoyoDialog("Hi there, I am Yoyo! What tasks do you have in mind?", yoyoImage)
        );
    }

    /**
     * Sets up Yoyo instance for MainWindow.
     *
     * @param y Yoyo instance.
     */
    public void setYoyo(Yoyo y) {
        yoyo = y;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yoyo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoyo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYoyoDialog(response, yoyoImage)
        );
        userInput.clear();
        if (response.equals("bye")) {
            Platform.exit();
        }
    }


}
