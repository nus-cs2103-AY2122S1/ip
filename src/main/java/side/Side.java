package side;

import java.util.Scanner;

import javafx.application.Application;
import side.exception.SideException;
import side.exception.UnknownCommandException;
import side.gui.SideGui;
import side.util.Parser;
import side.util.TaskList;
import side.util.Ui;

/**
 * Contains logic for prompting user for input and directing flow of commands.
 *
 * On running the program, Side greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a Task of x with no date/time attached
 *   - 'deadline x /by y' -> adds Deadline of x that needs to be done by y
 *   - 'event x /at y /to z' -> adds an Event of x that starts at datetime y and ends on datetime z
 *   - 'list' -> displays current list of tasks
 *   - 'done x' -> marks Task x as done
 *   - 'delete x' -> deletes Task x from the task list
 *   - 'find x' -> returns tasks with description or a substring of description matching x
 *   - 'bye' -> exits the program
 *   - Other input patterns throw exceptions
 *
 * @author Lua Yi Da
 */

public class Side {

    private Ui ui;
    private TaskList tasks;
    private Scanner scanner;

    /**
     * Initialises a new instance of Side, with Ui, TaskList and Scanner.
     */
    public Side() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Crafts responses to user input.
     *
     * @param userInput String representing user input.
     * @param tasks List of tasks.
     * @return String corresponding to response to command.
     */
    public String replyToCommand(String userInput, TaskList tasks) {
        Parser.COMMAND command = Parser.toCommand(userInput.split("\\s+")[0]);

        try {
            switch (command) {
            case TODO:
                return ui.addTask(userInput, tasks);
            case DEADLINE:
                return ui.addDeadline(userInput, tasks);
            case EVENT:
                return ui.addEvent(userInput, tasks);
            case LIST:
                return tasks.toString();
            case DONE:
                return ui.handleDone(userInput, tasks);
            case DELETE:
                return ui.handleDelete(userInput, tasks);
            case FIND:
                return ui.handleFind(userInput, tasks);
            case BYE:
                return ui.handleClose();
            default:
                throw new UnknownCommandException();
            }
        } catch (SideException e) {
            return e.getMessage();
        }
    }

    /**
     * Launches GUI.
     *
     * @param args Standard main method args.
     */
    public static void main(String[] args) {
        Application.launch(SideGui.class, args);
    }
}
