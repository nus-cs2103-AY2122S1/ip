package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String INSTRUCTION_DEADLINE = "deadline";
    protected Deadline deadline;

    /**
     * Class constructor for DeadlineCommand Class specifying parameter_1 and parameter_2
     */
    public DeadlineCommand(String parameter1, String parameter2) throws DukeException {
        if (parameter1.equals("") || parameter2.equals("")) {
            throw new DukeException("☹ OOPS!!! The description or the date a deadline cannot be empty.");
        }
        deadline = new Deadline(parameter1, parameter2);
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.deadline);
        tasks.add(this.deadline);
        ui.formatPrint("Got it. I've added this task:", "  " + this.deadline.toString(), tasks.toString());
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_DEADLINE + "] - " + deadline.toString();
    }
}
