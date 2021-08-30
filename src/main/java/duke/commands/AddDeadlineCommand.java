package duke.commands;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;

/**
 * Command that adds deadline to task list.
 */
public class AddDeadlineCommand extends Command {
    private final String by;
    private final LocalDate date;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc The description of the deadline.
     * @param by   when the deadline is.
     */
    public AddDeadlineCommand(String desc, String by) {
        super(desc);
        this.by = by;
        this.date = null;
    }

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc The description of the deadline.
     * @param by   when the deadline is.
     * @param date date of deadline.
     */
    public AddDeadlineCommand(String desc, String by, LocalDate date) {
        super(desc);
        this.by = by;
        this.date = date;
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public void execute(TaskList tasks) throws IOException {
        Deadline deadline;
        if (date == null) {
            deadline = new Deadline(super.getDesc(), by, false);
        } else {
            deadline = new Deadline(super.getDesc(), by, false, date);
        }

        tasks.add(deadline);

        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
