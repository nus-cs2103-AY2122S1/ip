package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Duke is a chatbot that keeps track of your tasks for you.
 *
 * Current Progress: Level 10. GUI
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
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Duke.
     * Initialises the classes that are necessary for Duke to run.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.retrieveData(), storage);
        parser = new Parser(tasks);
    }

    /**
     * Gets the response from Duke upon receiving user's input command.
     *
     * @param input String containing the user's input command.
     * @return String containing Duke's response to the user's input command.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.getCommand(input);
            return command.execute();
        } catch (DukeException e) {
            return e.toString();
        }

    }
}
