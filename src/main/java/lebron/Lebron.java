package lebron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lebron.task.Deadline;
import lebron.task.Events;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Lebron extends Application {

    final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    public static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image lebron = new Image(this.getClass().getResourceAsStream("/images/lebron.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/blank.png"));
    /**
     * Available commands that the bot can understand.
     */
    public enum Command {
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        OTHER(" ");

        private String text;

        Command(String text) {
            this.text = text;
        }

        /**
         * Creates a Command enum value from a string.
         *
         * @param text the text.
         * @return the desired Command enum.
         */
        public static Command fromString(String text) {
            for (Command c : Command.values()) {
                if (c.text.equalsIgnoreCase(text)) {
                    return c;
                }
            }
            return OTHER;
        }
    }

    /**
     * Constructor for the chatbot.
     *
     * @throws IOException if stream to file is missing or invalid.
     */
    public Lebron() throws IOException {
        this.ui = new Ui(this);
        this.storage = new Storage(FILE_PATH);
        try {
            ArrayList<Task> loadList = storage.loadFileContents(FILE_PATH);
            taskList = new TaskList(loadList, this);
        } catch (Exception e) {
            ArrayList<Task> loadList = new ArrayList<>();
            taskList = new TaskList(loadList, this);
        }
    }

    /**
     * Runs the bot.
     *
     * @throws IOException if stream to file is missing or invalid.
     */
    public String run(String text) throws IOException {
        int position = 0;
        Parser parser = new Parser();
        String reply = "";

        if (!text.equals("bye")) {
            String commandWord = parser.parseText(text);
            String[] splitWords = parser.split(text);
            Command command = Command.fromString(commandWord);
            switch (command) {
            case LIST:
                reply = ui.replyDisplay(taskList);
                break;
            case DONE:
                int pos = Integer.parseInt(splitWords[1]);
                reply = taskList.markDone(pos - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case TODO:
                try {
                    reply = taskList.add(new ToDo(splitWords[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description of a todo cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case DEADLINE:
                try {
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    reply = taskList.add(new Deadline(splitBy[0], splitBy[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description or a time of a deadline cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case EVENT:
                try {
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    reply = taskList.add(new Events(splitAt[0], splitAt[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description or a time of an event cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case DELETE:
                int pos2 = Integer.parseInt(splitWords[1]);
                reply = taskList.delete(pos2 - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case FIND:
                String keyword = splitWords[1];
                reply = ui.replyFind(taskList, keyword);
                break;
            case OTHER:
                reply = HORIZONTAL_LINE
                        + "    :( OOPS! I'm sorry, but I don't know what that means.\n"
                        + HORIZONTAL_LINE;
                break;
            default:
                break;
            }
        }
        else {
            reply = ui.exit();
        }
        return reply;
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        Label greeting = new Label(ui.greet());

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Lebron");
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

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, new ImageView(lebron))
        );

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private Node getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label lebronText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(lebronText, new ImageView(lebron))
        );
        userInput.clear();
    }

    private String getResponse(String input) throws IOException {
        return run(input);
    }


}
