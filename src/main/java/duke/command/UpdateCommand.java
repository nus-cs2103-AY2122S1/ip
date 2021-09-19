package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Represents the command of updating a task to the list.
 */
public class UpdateCommand extends Command {

    private int index;
    private String attribute;
    private String newValue;

    public UpdateCommand(int index, String description) throws DukeException {
        this.index = index;
        splitDescription(description);
    }

    /**
     * Splits the description to the attribute and new value.
     * @param description The full description.
     */
    private void splitDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("OOPS!! you need to specify the attribute you want to change and the new value.");
        }

        if (!description.contains("/")) {
            throw new DukeException("OOPS!! update command has to be in the format:\n" +
                    "update [taskIndex] [taskAttribute] /[newValue]");
        }

        String[] split = description.split("/");

        attribute = split[0].replaceAll("\\s","").toLowerCase();;

        if (split.length <= 1) {
            throw new DukeException("OOPS!! you need to specify a new value for the update.");
        }

        newValue = split[1];
    }

    /**
     * Updates an attribute of task in index from the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     * @throws DukeException If index or new value is invalid or something goes wrong while saving.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize()) {
            throw new DukeException("OOPS!! task index is invalid.");
        }

        if (newValue.equals("")) {
            throw new DukeException("OOPS!! you need to specify a new value for the update.");
        }

        updateValue(tasks);

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }

        return ui.updateResponse(tasks.getTask(index));
    }

    private void updateValue(TaskList tasks) throws DukeException {
        Task task = tasks.getTask(index);

        switch (attribute) {
        case "description":
            task.setDescription(newValue);
            break;
        case "date":
            if (!(task instanceof DeadlineTask)) {
                throw new DukeException("OOPS!! this attribute is for a deadline task");
            }

            ((DeadlineTask) task).setDate(newValue);
            break;
        case "time":
            if (!(task instanceof EventTask)) {
                throw new DukeException("OOPS!! this attribute is for a deadline task");
            }
            ((EventTask) task).setTime(newValue);
            break;
        default:
            throw new DukeException("OOPS!! invalid attribute.\n" +
                    "For todo task the attributes are: description\n" +
                    "For deadline task the attributes are: description or date\n" +
                    "For event task the attributes are: description or time");
        }
    }
}
