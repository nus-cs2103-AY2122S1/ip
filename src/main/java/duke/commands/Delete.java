package duke.commands;

import duke.exceptions.InvalidTaskIdException;
import duke.utils.TaskList;

public class Delete extends Command {
    private int index;

    public Delete(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList) throws InvalidTaskIdException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new InvalidTaskIdException();
        }
        taskList.delete(index - 1);
        return taskList;
    }
}
