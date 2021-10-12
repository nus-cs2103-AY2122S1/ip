package duke.command;

import duke.DukeList;
import duke.task.ToDo;

public class TodoCommand extends DukeCommand {
    private final ToDo task;

    /**
     * Public constructor to create a TodoCommand.
     *
     * @param task Task to be added.
     */
    public TodoCommand(ToDo task) {
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
