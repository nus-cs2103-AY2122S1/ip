package duke;

import duke.gui.DukeDialogBox;
import duke.gui.DukeGui;
import duke.gui.UserDialogBox;
import duke.task.Task;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke extends DukeGui implements duke.ChatbotUI, duke.Parser {
    private static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL_MESSAGE = "See you soon! :)";
    private static final String FAREWELL_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String COMPLETE_TASK_COMMAND = "done";
    private static final String FIND_TASK_COMMAND = "find";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String CREATE_TODO_COMMAND = "todo";
    private static final String CREATE_EVENT_COMMAND = "event";
    private static final String CREATE_DEADLINE_COMMAND = "deadline";

    private Storage storage;
    private TaskList taskList;

    private Scanner sc;

    /**
     * The entrypoint of the Duke chat bot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

        duke.greet();
        duke.run();
    }

    /**
     * A constructor for Duke chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage("../../../../data/duke_storage.txt");
        this.loadData();
        this.sc = new Scanner(System.in);
    }


    /**
     * Starts the JavaFX GUI.
     * @param stage The primary stage that JavaFX provides
     */
    @Override
    public void start(Stage stage) {
        setUpGuiComponents(stage);

        // Handles user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(taskMode(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new UserDialogBox(userText, new ImageView(user)),
                new DukeDialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Loads data that is saved in a given filename, and parses the data to load tasks.
     */
    public void loadData() {
        ArrayList<String> lines = this.storage.readLinesFromFile();
        for (int i = 0; i < lines.size(); i++) {
            Task task = Task.parseTaskFromSavedText(lines.get(i));
            this.taskList.addTask(task);
        }
        ChatbotUI.printMessage("Loaded tasks from save file!" + this.taskList.countTasks());
    }

    /**
     * Saves Chatbot data to a given filename.
     */
    public void saveData() {
        String content = this.taskList.toSaveData();
        this.storage.overwriteNewFile();
        this.storage.writeToFile(content);
    }

    /**
     * Handles the logic when Duke is ended.
     */
    public void endDuke() {
        this.saveData();
        ChatbotUI.printMessage(FAREWELL_MESSAGE);
    }

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        ChatbotUI.printMessage(GREETING_MESSAGE);
    }

    public void run() {
        String msg = ChatbotUI.acceptUserInput(this.sc).trim();
        String output = taskMode(msg);
        ChatbotUI.printMessage(output);
        run();
    }

    /**
     * Handles the logic for managing a user's tasks.
     */
    public String taskMode(String msg) {
        if (msg.equals(FAREWELL_COMMAND)) {
            this.endDuke();
            return "I've saved the tasks. You can close Duke now!";
        }
        try {
            TaskList tasks = this.taskList;
            if (msg.equals(LIST_COMMAND)) {
                return tasks.toString();
            } else if (msg.startsWith(COMPLETE_TASK_COMMAND)) {
                return tasks.completeTask(Parser.getIntFrom(COMPLETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(FIND_TASK_COMMAND)) {
                return tasks.findTasks(Parser.getStringFrom(FIND_TASK_COMMAND, msg));
            } else if (msg.startsWith(DELETE_TASK_COMMAND)) {
                return tasks.deleteTask(Parser.getIntFrom(DELETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(CREATE_TODO_COMMAND)) {
                return tasks.addNewTodo(Parser.getStringFrom(CREATE_TODO_COMMAND, msg));
            } else if (msg.startsWith(CREATE_EVENT_COMMAND)) {
                return tasks.addNewEvent(Parser.getStringFrom(CREATE_EVENT_COMMAND, msg));
            } else if (msg.startsWith(CREATE_DEADLINE_COMMAND)) {
                return tasks.addNewDeadline(Parser.getStringFrom(CREATE_DEADLINE_COMMAND, msg));
            } else {
                throw new DukeException("I don't know what that command means." +
                        "\nPlease input a valid command.");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
