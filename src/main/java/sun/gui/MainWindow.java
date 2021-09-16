//@@author @fyshhh-reused
package sun.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sun.Sun;
import sun.ui.Ui;

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

    private Sun Sun;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image SunImage = new Image(this.getClass().getResourceAsStream("/images/DaSun.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/DaBg.JPEG"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        //@@author ChengJiyuqing-reused
        BackgroundImage bgImage = new BackgroundImage(this.bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        dialogContainer.setMaxHeight(Double.POSITIVE_INFINITY);
        Background background = new Background(bgImage);
        dialogContainer.setBackground(background);
        //@@author
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getSunDialog(welcomeMessage, new ImageView(SunImage)));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    public void setSun(Sun d) {
        Sun = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sun's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Sun.getResponse(input);
        // @@author zihaooo9-reused
        if (response.contains("Til we meet again, bye!")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
        // @@author
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, new ImageView(userImage)),
                DialogBox.getSunDialog(response, new ImageView(SunImage))
        );
        userInput.clear();
    }
}
// @@author
