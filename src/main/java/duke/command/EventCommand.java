package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The event command.
 */
public class EventCommand extends Command {

    /**
     * The arguments associated with the command
     **/
    private String arguments;

    /**
     * Constructs the event command.
     *
     * @param arguments The arguments associated with the command.
     */
    public EventCommand(String arguments) {
        super("event");
        this.arguments = arguments;
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @return The output of executing the command.
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Parser.checkAddTaskArgument(this.getCommand(), this.arguments);
        String[] argArr = Parser.parseArguments(this.getCommand(), this.arguments);
        LocalDate newTaskDate = Parser.convertDate(argArr[1]);
        Event newTask = new Event(argArr[0], newTaskDate);
        tasks.add(newTask);
        return "Got it. I've added this task:\n" + "  "
                + newTask + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.";
    }

}
