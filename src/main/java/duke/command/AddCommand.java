package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.MismatchedFormException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents the class to specify how to add command.
 */
public class AddCommand extends Command {
    private String response;
    private Operation type;
    private int splitIndex;
    private String splitString;

    /**
     * Adds the command.
     *
     * @param response The content of user input.
     * @param type The type of adding command.
     */
    public AddCommand(String response, Operation type,int splitIndex, String splitString) {
        this(response, type, splitIndex);
        this.splitString = splitString;
    }

    public AddCommand(String response, Operation type, int splitIndex) {
        this.response = response;
        this.type = type;
        this.splitIndex = splitIndex;
    }

    /**
     * Shows the task just be added.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = new Task("");
        switch (type) {
        case TODO:
            task = new Todo(response.substring(splitIndex));
            break;
        case DEADLINE:
            task = new Deadline(partsForEventOrDeadline()[0], partsForEventOrDeadline()[1]);
            break;
        case EVENT:
            task = new Event(partsForEventOrDeadline()[0], partsForEventOrDeadline()[1]);
            break;
        default:
            break;
        }
        tasks.addElement(task);
        String taskString = task.toString();
        storage.store(taskString);
        return ui.showAdding(taskString, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Provides required parameter for deadline or event.
     *
      * @return The string array that contains their required parameter.
     * @throws MismatchedFormException The exception inherit from checkContent.
     */
    public String[] partsForEventOrDeadline() throws MismatchedFormException {
        String LaterPart = Parser.checkContent(response, splitIndex, splitString);
        String[] Parts = LaterPart.split(splitString);
        String Content = Parts[0];
        String Time = Task.formatOutputDateAndTime(Parts[1]);
        return new String[] {Content, Time};
    }
}
