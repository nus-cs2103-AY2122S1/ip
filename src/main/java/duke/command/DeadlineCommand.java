package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() == 8) {
                throw new DukeException(DukeException.Type.EmptyDeadline);
            } else {
                String[] processedInput = input.substring(9).split(" /by ", 2);
                Deadline d = new Deadline(processedInput[0], processedInput[1]);
                storage.addTask(d);
                taskList.addTask(d);
                return Ui.printTaskCreated(d, taskList.getNumTasks());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
