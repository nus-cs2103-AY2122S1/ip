package lebron;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import lebron.exception.LebronException;
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
    private static final String HORIZONTAL_LINE = "    ____________________________" +
            "________________________________\n";
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
     */
    public Lebron() {
        this.ui = new Ui(this);
        this.storage = new Storage(FILE_PATH);
        ArrayList<Task> loadList = storage.loadFileContents(FILE_PATH);
        taskList = new TaskList(loadList, this);
    }

    /**
     * Runs the bot.
     *
     */
    public String run(String text) {
        Parser parser = new Parser();
        String reply = "";
        try {
            if (text.equals("bye")) {
                reply = ui.exit();
            } else {
                String commandWord = parser.parseText(text);
                String[] splitWords = parser.split(text);
                Command command = Command.fromString(commandWord);
                switch (command) {
                case LIST:
                    reply = ui.replyDisplay(taskList);
                    break;
                case DONE:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! Please specify which task you wish "
                                + "to complete.");
                    }
                    int pos = Integer.parseInt(splitWords[1]);
                    reply = taskList.markDone(pos - 1);
                    storage.saveToFile(taskList.getLst());
                    break;
                case TODO:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! The description of a ToDo "
                                + "cannot be empty.");
                    }
                    reply = taskList.add(new ToDo(splitWords[1]));
                    storage.saveToFile(taskList.getLst());
                    break;
                case DEADLINE:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! The description of a Deadline " +
                                "cannot be empty.");
                    }
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    if (splitBy.length < 2 || splitBy[1].equals("")) {
                        throw new LebronException("    :( OOPS! Please check that the '/by' keyword " +
                                "is used and that a due date is given.");
                    }
                    reply = taskList.add(new Deadline(splitBy[0], splitBy[1]));
                    storage.saveToFile(taskList.getLst());
                    break;
                case EVENT:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! The description of an Event " +
                                "cannot be empty.");
                    }
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    if (splitAt.length < 2 || splitAt[1].equals("")) {
                        throw new LebronException("    :( OOPS! Please check that the '/at' keyword " +
                                "is used and that a due date is given.");
                    }
                    reply = taskList.add(new Events(splitAt[0], splitAt[1]));
                    storage.saveToFile(taskList.getLst());

                    break;
                case DELETE:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! Please specify which task you wish "
                                + "to delete.");
                    }
                    int pos2 = Integer.parseInt(splitWords[1]);
                    reply = taskList.delete(pos2 - 1);
                    storage.saveToFile(taskList.getLst());
                    break;
                case FIND:
                    if (splitWords.length < 2 || splitWords[1].equals("")) {
                        throw new LebronException("    :( OOPS! Please specify some words to search for.");
                    }
                    String keyword = splitWords[1];
                    reply = ui.replyFind(taskList, keyword);
                    break;
                case OTHER:
                    throw new LebronException(" OOPS! I'm sorry, but I don't know what that means.\n");
                default:
                    break;
                }
            }
            return reply;
        } catch (LebronException e) {
            return ui.printException(e.getMessage());
        }
    }
}
