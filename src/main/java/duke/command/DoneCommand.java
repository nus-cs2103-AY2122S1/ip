package duke.command;

import duke.DukeList;
import duke.exception.InvalidArgumentsException;
import duke.task.Task;

public class DoneCommand extends DukeCommand {
    private final int id;

    /**
     * Public constructor to create a DoneCommand.
     *
     * @param id Id of task.
     */
    public DoneCommand(int id) {
        super();
        this.id = id;
    }

    @Override
    public String run(DukeList list) throws InvalidArgumentsException {
        try {
            Task task = list.getTask(id);
            task.markDone();
            return stringifyMessage(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }

    private String stringifyMessage(String taskString) {
        return "Nice! I've marked this task as done:\n"
                + taskString;
    }
}
