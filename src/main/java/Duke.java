import java.io.FileNotFoundException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

/**
 * <h1> Duke TaskList ChatBot! </h1>
 * The duke program implements and application that keeps track of all the users task!
 * The user is able to add, delete and "mark as complete" tasks from the task list!
 * Currently the Duke ChatBot is only able to keep track of ToDos, Deadlines and Events!
 *
 * @author Ong Cheng Seong
 * @version 1.0
 * @since 2021-08-31
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke ChatBot Object that has a Ui, and a TaskList populated by the data read from the storage.
     * @param filePath text document that contains the task list for Storage to read from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = loadTaskList(storage);
    }

    /**
     * Returns the response given by the input
     * @param input User Input
     * @return Response after command is executed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            assert !output.equals("") : "There should be an output";
            return output;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private TaskList loadTaskList(Storage storage) {
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        return tasks;
    }

}
