package duke.command;

import duke.DukeList;
import duke.exception.InvalidArgumentsException;
import duke.task.Task;

public class DeleteCommand extends DukeCommand {
    private final int id;

    /**
     * Public constructor to create a DeleteCommand.
     *
     * @param id Id of task.
     */
    public DeleteCommand(int id) {
        super();
        this.id = id;
    }

    @Override
    public String run(DukeList list) throws InvalidArgumentsException {
        try {
            Task task = list.getTask(id);
            list.deleteTask(id);
            return stringifyMessage(task.toString(), list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }

    private String stringifyMessage(String taskString, int listSize) {
        return "Noted. I've removed this task:\n"
                + taskString + "\n"
                + "Now you have " + listSize + " tasks in the list";
    }
}
