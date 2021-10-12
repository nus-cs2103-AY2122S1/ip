package duke.command;

import duke.DukeList;
import duke.task.Deadline;

public class DeadlineCommand extends DukeCommand {
    private final Deadline task;

    /**
     * Public constructor to create a DoneCommand.
     *
     * @param task Deadline to be added.
     */
    public DeadlineCommand(Deadline task) {
        super();
        this.task = task;
    }

    @Override
    public String run(DukeList list) {
        list.addTask(task);
        return stringifyMessage(list.size());
    }

    private String stringifyMessage(int listSize) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + listSize + " tasks in the list";
    }
}
