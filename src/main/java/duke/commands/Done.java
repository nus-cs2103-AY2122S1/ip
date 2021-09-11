package duke.commands;

import duke.exceptions.InvalidTaskIdException;
import duke.utils.TaskList;

public class Done extends Command {
    private int index;

    public Done(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList) throws InvalidTaskIdException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new InvalidTaskIdException();
        }
        taskList.get(index - 1).markAsCompleted();
        return taskList;
    }
}
