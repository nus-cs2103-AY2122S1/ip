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
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                    + "Please try again.", this.getCommand()));
        }

        String[] argArr = arguments.split("/at");
        if (argArr.length == 1 || argArr[1].isEmpty()) {
            throw new DukeException("Arguments do not follow proper format. Don't forget the /at");
        }

        LocalDate newTaskDate = Parser.convertDate(argArr[1].trim());
        Event newTask = new Event(argArr[0].trim(), newTaskDate);
        tasks.add(newTask);
        ui.printToUser("Got it. I've added this task:");
        ui.printToUser("  " + newTask);
        ui.printToUser("Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.");
    }

    /**
     * Checks whether command terminate the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
