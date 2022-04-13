package yoyo.gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import yoyo.Yoyo;

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
    @FXML
    private Button sendButton;

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
        userInput.setFont(Font.font ("serif", 14));
        sendButton.setFont(Font.font ("serif", 14));
    }


    /**
     * Executes actions to be completed when main windows is rendered.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getYoyoDialog("Hi there, I am Yoyo! What tasks do you have in mind?", yoyoImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yoyo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYoyoDialog(response, yoyoImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            Platform.exit();
        }
    }


}
