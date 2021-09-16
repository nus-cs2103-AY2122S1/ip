package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that kick-starts duke.Duke.
 *
 * @author Benjamin Lui
 */

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final String filePath = "tasks.txt";
    private final double vValue = 1.0;
    private final double stageMinHeight = 600.0;
    private final double stageMinWidth = 400.0;
    private final int scrollPaneWidth = 385;
    private final int scollPaneHeight = 535;
    private final double userWidth = 325.0;
    private final double sendButtonWidth = 55.0;

    /**
     * Constructor for initialising duke.Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();

        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    String displayWelcome() {
        return ui.showWelcome(tasks);
    }

    /**
     * Initialises duke.Duke.
     * @param args None required
     */
    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) {
        // step 1
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(new Label(ui.showWelcome(tasks)));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("duke.Duke");
        stage.setResizable(false);
        stage.setMinHeight(stageMinHeight);
        stage.setMinWidth(stageMinWidth);

        mainLayout.setPrefSize(stageMinWidth, stageMinHeight);

        scrollPane.setPrefSize(scrollPaneWidth, scollPaneHeight);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(vValue);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(userWidth);

        sendButton.setPrefWidth(sendButtonWidth);

        AnchorPane.setTopAnchor(scrollPane, vValue);

        AnchorPane.setBottomAnchor(sendButton, vValue);
        AnchorPane.setRightAnchor(sendButton, vValue);

        AnchorPane.setLeftAnchor(userInput , vValue);
        AnchorPane.setBottomAnchor(userInput, vValue);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(vValue));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     *
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getDukeDialog(dukeText.getText(), duke)
        );
        userInput.clear();
    }

    /**
     * Returns a response from Duke.
     * @param input the input string from the user
     */
    public String getResponse(String input) {
        String fullCommand = input;
        Command c = new Parser().parse(fullCommand);
        return c.execute(tasks, ui, storage);


    }
}
