package bot.utility;

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

import java.util.Objects;

/**
 * Simulates the Duke chatBot.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private Parser parser;
    private Logger logger;
    private TaskList tasks;
    private Ui ui;

    /**
     * Runs the Duke chatBot
     */
    public void run() {
        start();
        analyzeLog();
    }

    private void start() {
        logger = new Logger("tasks.txt");
        parser = new Parser();
        ui = new Ui();
        ui.greet();
        tasks = new TaskList(logger.loadList());
    }

    /**
     * Primary response function of the chatBot
     */
    private void analyzeLog() {
        do {
            String input = ui.getUserInput();
            parser.takeInput(input);
            parser.interactWith(ui, logger, tasks);
        } while (parser.flag == 1);
        ui.close();
    }

    @Override
    public void start(Stage stage) {
        start();
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(800);

        scrollPane.setPrefSize(681, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(681.0);

        sendButton.setPrefWidth(100.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
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
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * Generate a response to user input.
     */
    protected String getResponse(String input) {
        parser.takeInput(input);
        return parser.interactWith(ui, logger, tasks);
    }
}
