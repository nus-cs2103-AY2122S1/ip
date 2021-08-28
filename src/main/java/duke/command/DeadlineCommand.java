package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The deadline command.
 */
public class DeadlineCommand extends Command {

    /**
     * The arguments associated with the command
     **/
    private String arguments;

    /**
     * Constructs the deadline command.
     *
     * @param arguments The arguments associated with the command.
     */
    public DeadlineCommand(String arguments) {
        super("deadline");
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

        String[] argArr = arguments.split("/by");
        if (argArr.length == 1 || argArr[1].isEmpty()) {
            throw new DukeException("Arguments do not follow proper format. Don't forget the /by");
        }

        LocalDate newTaskDate = Parser.convertDate(argArr[1].trim());
        Deadline newTask = new Deadline(argArr[0].trim(), newTaskDate);
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
