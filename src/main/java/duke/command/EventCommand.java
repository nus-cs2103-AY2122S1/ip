package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() == 5) {
                throw new DukeException(DukeException.Type.EmptyEvent);
            } else {
                String[] processedInput = input.substring(6).split(" /at ", 2);
                Event ev = new Event(processedInput[0], processedInput[1]);
                storage.addTask(ev);
                taskList.addTask(ev);
                return Ui.printTaskCreated(ev, taskList.getNumTasks());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
