package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 9. Find
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *
 *   Add Tasks:
 *   - 'todo x' -> adds a ToDo task of x with no date/time attached
 *   - 'deadline x /by a b' -> adds a Deadline task of x that needs to be done by date a and time b (time is optional)
 *   - 'event x /at a b c' -> adds an Event task of x that is on date a and starts at time b and ends at time c
 *
 *   Display Tasks
 *   - 'list' -> displays current list of tasks
 *   - 'check x' -> displays list of tasks due on date x
 *   - 'find x' -> displays list of tasks that contains the keyword x in their description
 *
 *   Complete Tasks
 *   - 'done x' -> marks Task x as done
 *
 *   Remove Tasks
 *   - 'delete x' -> deletes Task x from the task list
 *
 *   Exit Program
 *   - 'bye' -> exits the program
 *
 *   Invalid Commands
 *   - any other String of different patterns -> throws an exception
 *
 * CS2103T Individual Project AY 21/22 Sem 1
 * @author Benedict Chua
 */
public class Duke {
    /**
     * Main method for Duke.
     * Initialises Duke and starts taking in commands from the user.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Initialise program
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.retrieveData(), storage);
        Parser parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        // Greets user
        Ui.printLineSeparator();
        Ui.showWelcome();
        Ui.printLineSeparator();
        Ui.printEmptyLine();

        // Carries out commands inputted by user into the Scanner
        while (!isExit) {
            try {
                Ui.printLineSeparator();
                String input = sc.nextLine();
                Command command = parser.getCommand(input);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.displayMessage(new String[] {e.toString()});
            } finally {
                Ui.printLineSeparator();
                Ui.printEmptyLine();
            }
        }
    }
}
