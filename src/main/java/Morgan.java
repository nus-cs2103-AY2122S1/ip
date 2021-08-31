import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import commands.Command;
import exceptions.MorganException;
import parser.CommandParser;
import storage.Storage;
import tasks.TaskList;


public class Morgan extends Application {
    public static final String EXIT_KEYWORD = "bye";
    private final TaskList taskList = new TaskList();
    private final Ui ui;
    private final Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morganImage = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));

    public Morgan() {
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public static void main(String[] args) {
        Morgan morgan = new Morgan();
        morgan.run();
    }

    private void run() {
        CommandParser p = new CommandParser();
        String userInput = this.ui.getInput();
        boolean isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
        while (!isExitKeyword) {
            try {
                Command command = p.getCommand(userInput);
                String output = command.execute(this.taskList, this.storage);
                this.ui.print(output);
            } catch (MorganException e) {
                this.ui.print(e.getMessage());
            }

            userInput = this.ui.getInput();
            isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
        }

        this.ui.printEndDisplay();
}

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Morgan");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        dialogContainer.getChildren().add(DialogBox.getMorganDialog(ui.startGreeting(), morganImage));

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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String morganText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getMorganDialog(morganText, morganImage)
        );
        userInput.clear();

        if (userText.trim().equalsIgnoreCase(EXIT_KEYWORD)) {
            Platform.exit();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        Command command;
        CommandParser cp = new CommandParser();
        try {
            command = cp.getCommand(input);
            output = command.execute(taskList, this.storage);
        } catch(MorganException e) {
            output = e.getMessage();
        }
        return output;
    }


}
