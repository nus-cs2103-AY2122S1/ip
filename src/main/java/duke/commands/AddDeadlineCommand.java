package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.TaskList;
import duke.tasks.Deadline;

/**
 * Command that adds deadline to task list.
 */
public class AddDeadlineCommand extends Command {
    private final LocalDateTime by;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc The description of the deadline.
     * @param by   when the deadline is.
     */
    public AddDeadlineCommand(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        Deadline deadline = new Deadline(super.getDesc(), by, false);
        tasks.add(deadline);

        StringBuilder replyBuilder = new StringBuilder();

        replyBuilder.append("Got it. I've added this task:\n");
        replyBuilder.append(deadline + "\n");

        if (tasks.size() == 1) {
            replyBuilder.append("Now you have 1 task in the list. \n");
        } else {
            replyBuilder.append("Now you have " + tasks.size() + " tasks in the list. \n");
        }

        return replyBuilder.toString();
    }
}
