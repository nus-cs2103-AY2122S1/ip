package duke;

import duke.exception.DukeException;
import duke.exception.OutOfBoundException;

public class MarkAsDoneCommand extends UndoableCommand {
    public MarkAsDoneCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * A method to mark a task as done.
     *
     * @return Task after it is marked as done.
     * @throws DukeException Thrown when user inputs task number that is out of the given task range.
     * @throws NumberFormatException Thrown when user gives a non-number as the task number.
     */
    @Override
    public String run() throws DukeException, NumberFormatException {
        String i = input.substring(input.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > tasks.getList().size()) {
            throw new OutOfBoundException();
        }
        return tasks.markAsDone(index);
    }

    @Override
    public String undo() {
        String i = input.substring(input.length()-1);
        int index = Integer.parseInt(i);
        return tasks.markAsUndone(index);
    }
}
