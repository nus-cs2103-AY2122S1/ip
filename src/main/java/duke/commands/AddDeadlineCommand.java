package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.tasks.Deadline;
import duke.Storage;
import duke.TaskList;

/**
 * Command that adds deadline to task list.
 */
public class AddDeadlineCommand extends Command {
    private final String by;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc The description of the deadline.
     * @param by   when the deadline is.
     */
    public AddDeadlineCommand(String desc, String by) {
        super(desc);
        this.by = by;
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
     * @param tasks   the task list.
     * @param storage the storage of the programme.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Deadline deadline = new Deadline(super.getDesc(), by, false);
            tasks.add(deadline);
            storage.add(deadline);

            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
