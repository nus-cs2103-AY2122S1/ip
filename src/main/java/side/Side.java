package side;

import java.util.Scanner;

import side.exception.UnknownCommandException;
import side.util.Ui;
import side.util.TaskList;
import side.util.Parser;
import side.exception.SideException;

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
 *   - 'find x' -> returns tasks with description matching x
 *   - 'bye' -> exits the program
 *   - Other input patterns throw exceptions
 *
 * @author Lua Yi Da
 */

public class Side {

    private Ui ui;
    private TaskList tasks;
    private Scanner scanner;

    public Side() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.scanner = new Scanner(System.in);
    }

    private void run() {
        ui.greet();
        String history = tasks.retrieve();
        ui.printResponse(history);

        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            Parser.COMMAND command = Parser.toCommand(userInput.split("\\s+")[0]);

            try {
                switch (command) {
                case TODO:
                    ui.addTask(userInput, tasks);
                    break;
                case DEADLINE:
                    ui.addDeadline(userInput, tasks);
                    break;
                case EVENT:
                    ui.addEvent(userInput, tasks);
                    break;
                case LIST:
                    ui.printResponse(tasks.toString());
                    break;
                case DONE:
                    ui.handleDone(userInput, tasks);
                    break;
                case DELETE:
                    ui.handleDelete(userInput, tasks);
                    break;
                case FIND:
                    ui.handleFind(userInput, tasks);
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (SideException e) {
                ui.printResponse(e.getMessage());
            }
            userInput = scanner.nextLine();
        }
        tasks.save();
        ui.close();
    }

    public static void main(String[] args) {
        new Side().run();
    }
}
