package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class to handle all the GUI/JavaFX portion of Duke
 */
public class Gui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;
    private Duke duke;

    /**
     * Starts the GUI application of Duke.
     *
     * @param stage The stage passed on by Duke start()
     * @param duke Duke instance that was used to call this method
     */
    public void start(Stage stage, Duke duke) {
        this.duke = duke;
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");
        this.mainLayout = new AnchorPane();
        this.mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        // set stage
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // styling
        this.styleNodes(stage);

        this.dialogContainer.getChildren().add(new Label(Ui.showWelcomeMessage()));

        this.sendButton.setOnMouseClicked((event) -> this.handleUserInput());
        this.userInput.setOnAction((event) -> this.handleUserInput());
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void styleNodes(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        this.mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Handles the text input by the user.
     */
    private void handleUserInput() {
        DialogBox userText = DialogBox.getUserDialog(this.userInput.getText());
        DialogBox dukeText;
        String inputString = this.userInput.getText();
        String parsedString = "";

        if (inputString.isBlank()) {
            return;
        }

        try {
            parsedString = this.duke.handleInput(inputString);
            dukeText = DialogBox.getDukeDialog(Ui.showReply(parsedString));
        } catch (DukeException e) {
            dukeText = DialogBox.getErrorDialog(Ui.showErrorMessage(e));
        }
        this.dialogContainer.getChildren().addAll(userText, dukeText);

        this.duke.saveDataToFile();
        this.userInput.clear();

        if (parsedString.equals("bye")) {
            System.exit(0);
        }
    }
}
