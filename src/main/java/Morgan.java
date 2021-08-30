import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import commands.Command;
import exceptions.MorganException;
import tasks.TaskList;

public class Morgan extends Application {
    private static final String EXIT_KEYWORD = "bye";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morgan = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));


    public static void main(String[] args) {
        Morgan.run();
    }

    private static void run() {
        // Initialize Ui
        Ui ui = new Ui();
        ui.printStartDisplay();

        // Initialize TaskList and CommandParser
        TaskList tl = new TaskList();
        CommandParser p = new CommandParser();

        try {
            // Initialize and load storage
            Storage s = new Storage();
            s.load(tl);

            // Read user input and check whether input is exit keyword
            String userInput = ui.getInput();
            boolean isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
            while (!isExitKeyword) {
                try {
                    // Obtain and execute command to retrieve output message
                    Command command = p.getCommand(userInput);
                    String output = command.execute(tl);

                    // Save updated task list
                    s.save(tl);

                    // Print output message
                    ui.print(output);
                } catch (MorganException e) {
                    ui.print(e.getMessage());
                }

                // Retrieves user input and check if input is exit keyword
                userInput = ui.getInput();
                isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
            }

            // Print end display
            ui.printEndDisplay();
        } catch (MorganException e) {
            ui.print(e.getMessage());
        }
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
        scrollPane.setStyle("-fx-background-color: champagne pink");

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

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
    private Node getDialogLabel(String text) {
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
        //TODO: EDIT THIS
        String userText = userInput.getText();
        Label userTextLabel = new Label(userText);
        String morganText = getResponse(userText);
        Label morganTextLabel = new Label(morganText);

        ImageView userImageView = new ImageView(user);
        ImageView morganImageView = new ImageView(morgan);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userTextLabel, userImageView),
                DialogBox.getDukeDialog(morganTextLabel, morganImageView)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //TODO: EDIT THIS
        return "Morgan heard: " + input;
    }
}
