package duke.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui {
    private static final String DUKE_IMAGE_FILE_PATH = "/images/DaDuke.png";
    private static final String USER_IMAGE_FILE_PATH = "/images/DaUser.png";
    private static final float ANCHOR_WIDTH = 400;
    private static final float ANCHOR_HEIGHT = 600;
    private static final float SCROLL_PANE_WIDTH = 385;
    private static final float SCROLL_PANE_HEIGHT = 535;
    private static final float USER_INPUT_WIDTH = 325;
    private static final float BUTTON_WIDTH = 55;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_FILE_PATH));
    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_FILE_PATH));

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private static Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Gets the text that the user has typed into the textfield.
     *
     * @return Text that user has has typed into the textfield.
     */
    public String getUserInput() {
        return userInput.getText();
    }

    public void setHandleUserInput(EventHandler<? super MouseEvent> mouseEventHandler,
                                   EventHandler<ActionEvent> buttonEventHandler) {
        sendButton.setOnMouseClicked(mouseEventHandler);
        userInput.setOnAction(buttonEventHandler);
    }

    /**
     * Generates the dialog boxes.
     *
     * @param response Response given by Duke.
     */
    public void generateDialogBoxes(String response) {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }

    /**
     * Closes the window after 1 second.
     */
    public void exit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
    }

    private void formatWindow(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(ANCHOR_HEIGHT);
        stage.setMinWidth(ANCHOR_WIDTH);

        scrollPane.setPrefSize(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(USER_INPUT_WIDTH);
        sendButton.setPrefWidth(BUTTON_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setupFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets up the Gui.
     *
     * @param stage Default stage of a javafx application.
     */
    public void setup(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        mainLayout.setPrefSize(ANCHOR_WIDTH, ANCHOR_HEIGHT);

        formatWindow(stage);
        setupFunctionality();
    }
}
