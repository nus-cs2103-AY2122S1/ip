package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a Duke program, which is a Personal Assistant Chatbot
 * that helps a person to keep track of various tasks.
 */
public class Duke extends Application {

    TaskList userList;
    boolean isLoadSuccessful;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        isLoadSuccessful = loadData();
    }

    @Override
    public void start(Stage stage) {
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

        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

         */

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.getCommand(input);
        return "Duke: " + getResponse(command, input);
    }

    public static void main(String[] args) {
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (Exception exception) {
            System.out.println("Unexpected error: " + exception);
        }
    }

    private boolean loadData() {
        // Load data and list task
        try {
            userList = TaskList.load();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // Deprecated
    private void run() throws Exception {
        Ui.printDukeLogo();

        if (isLoadSuccessful) {
            Ui.printExistingUserWelcomeMessage();
            Ui.printLeftoverTaskMessage(userList);
        } else {
            userList = new TaskList();
            Ui.printNewUserWelcomeMessage();
        }

        Ui.printInputRequestMessage();

        while (true) {
            String userInput = Ui.getUserInput();
            Command command = Parser.getCommand(userInput);
            doCommand(command, userInput);
        }
    }

    private String getResponse(Command command, String userInput) {
        switch (command) {
        case BYE:
            return Response.byeMessage();
        case LIST:
            return userList.toString();
        case DONE:
            int number = Integer.parseInt(userInput.substring(5));
            userList.markDone(number);
            return Response.markTaskDoneMessage();
        case DELETE:
            number = Integer.parseInt(userInput.substring(7));
            userList.remove(number);
            return Response.removedTaskMessage();
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                System.out.println("Error: " + exception);
            }
            return Response.addedTaskMessage();
        case FIND:
            return userList.find(userInput.substring(5));
        case CLEAR:
            userList.clear();
            return Response.TaskListClearedMessage();
        case INVALID:
            return Response.invalidCommandMessage();
        default:
            return Response.invalidCommandMessage();
        }
    }

    // Deprecated
    private void doCommand(Command command, String userInput) throws Exception {
        switch (command) {
        case BYE:
            Ui.printByeMessage();
            break;
        case LIST:
            userList.list();
            break;
        case DONE:
            int number = Integer.parseInt(userInput.substring(5));
            userList.markDone(number);
            break;
        case DELETE:
            number = Integer.parseInt(userInput.substring(7));
            userList.remove(number);
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            try {
                userList.add(Parser.getTask(command, userInput));
            } catch (Exception exception) {
                System.out.println("Error: " + exception);
            }
            break;
        case FIND:
            userList.find(userInput.substring(5));
            break;
        case CLEAR:
            userList.clear();
            break;
        case INVALID:
            Ui.printInvalidCommandMessage();
            break;
        default:
            throw new Exception();
        }
    }
}
