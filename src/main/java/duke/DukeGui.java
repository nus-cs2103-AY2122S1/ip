package duke;

import java.util.Optional;

import duke.util.DukeDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DukeGui extends Application {

    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.png"));
    private Duke duke = new Duke(new DukeDB("./data/dukeStore.txt"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren()
                .addAll(scrollPane,
                        userInput,
                        sendButton);

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0,
                600.0);

        scrollPane.setPrefSize(385,
                535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane,
                1.0);

        AnchorPane.setBottomAnchor(sendButton,
                1.0);
        AnchorPane.setRightAnchor(sendButton,
                1.0);

        AnchorPane.setLeftAnchor(userInput,
                1.0);
        AnchorPane.setBottomAnchor(userInput,
                1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
        // more code to be added here later
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        Optional<String> rawDukeText = this.duke.takeInput(userInput.getText());
        Label dukeText = new Label(rawDukeText.orElseGet(() -> {
            stage.close();
            return "Bye.";
        }));
        dialogContainer.getChildren()
                .addAll(DukeDialogBox.getUserDialog(userText,
                        new ImageView(userImage)),
                        DukeDialogBox.getDukeDialog(dukeText,
                                new ImageView(dukeImage)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        Optional<String> output = this.duke.takeInput(input);

        return output.orElse("Bye");
    }
}
