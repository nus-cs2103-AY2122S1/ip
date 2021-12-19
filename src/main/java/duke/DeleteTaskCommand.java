package duke;

import duke.exception.DukeException;
import duke.exception.OutOfBoundException;

public class DeleteTaskCommand extends UndoableCommand {
    private Task deletedTask;
    public DeleteTaskCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * A method to delete a certain in a TaskList.
     *
     * @return Task after it is deleted.
     * @throws DukeException Thrown when user gives a task number that is outside of the range of TaskList.
     */
    @Override
    public String run() throws DukeException {
        String i = input.substring(input.length() - 1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > tasks.getList().size()) {
            throw new OutOfBoundException();
        }
        deletedTask = tasks.getList().get(index - 1);
        return tasks.deleteTask(index);
    }

    @Override
    public String undo() {
        assert deletedTask != null: "deletedTask should not be null here!";
        return tasks.addTask(deletedTask);
    }
}
