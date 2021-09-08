package lebron;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import lebron.task.Deadline;
import lebron.task.Events;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;

/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Lebron {
    public static final String FILE_PATH = "./data/duke.txt";
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image lebron = new Image(this.getClass().getResourceAsStream("/images/lebron.jpg"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/blank.png"));

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

        private final String text;

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
                    System.err.println("    :( OOPS! The description of a todo cannot be empty.\n");
                }
                break;
            case DEADLINE:
                try {
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    reply = taskList.add(new Deadline(splitBy[0], splitBy[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("    :( OOPS! The description or a time of a deadline cannot be empty.\n");
                }
                break;
            case EVENT:
                try {
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    reply = taskList.add(new Events(splitAt[0], splitAt[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("    :( OOPS! The description or a time of an event cannot be empty.\n");
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
                reply = "    :( OOPS! I'm sorry, but I don't know what that means.\n";
                break;
            default:
                break;
            }
        } else {
            reply = ui.exit();
        }
        return reply;
    }
}
