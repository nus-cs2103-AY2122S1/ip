package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DeadLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EventException;
import duke.exceptions.ToDoException;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.TaskList;
import duke.tasktypes.ToDo;

/**
 * Command that adds task to the list.
 */
public class AddCommand extends Command {

    private final String command;

    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            if (command.contains("todo")) {
                return executeToDo(command, taskList, ui, storage);
            } else if (command.contains("deadline")) {
                return executeDeadline(command, taskList, ui, storage);
            } else if (command.contains("event")) {
                return executeEvent(command, taskList, ui, storage);
            }
        } catch (DukeException e) {
            return ui.showError(e);
        }
        return "";
    }

    /**
     * Executes the task to do.
     *
     * @param command user input.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     * @return Returns response back to user
     * @throws ToDoException Exception to add description if empty.
     */
    public String executeToDo(String command, TaskList taskList, Ui ui, Storage storage) throws ToDoException {
        String updatedTask = command.replace("todo", "").trim();
        if (updatedTask.isBlank()) {
            throw new ToDoException("please add a description to your todo task");
        }
        ToDo toDoTask = new ToDo(updatedTask);
        taskList.add(toDoTask);
        storage.updateHardDisk(taskList);
        return ui.displayAdd(toDoTask);
    }

    /**
     * Executes the deadline.
     *
     * @param command user input.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     * @return Returns response back to user
     * @throws DeadLineException Exception to add description if empty.
     */
    public String executeDeadline(String command, TaskList taskList, Ui ui, Storage storage) throws DeadLineException {
        String updatedTask = command.replace("deadline", "").trim();
        if (updatedTask.isBlank()) {
            throw new DeadLineException("please add a description to your deadline task");
        }
        String deadlineToAdd = updatedTask.split("/by ")[0].trim();
        String finishBy = updatedTask.split("/by ")[1].trim();

        Deadline deadlineTask = new Deadline(deadlineToAdd, finishBy);
        taskList.add(deadlineTask);
        storage.updateHardDisk(taskList);
        return ui.displayAdd(deadlineTask);
    }

    /**
     * Executes the event.
     *
     * @param command user input.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     * @return Returns response back to user
     * @throws EventException Exception to add description if empty.
     */
    public String executeEvent(String command, TaskList taskList, Ui ui, Storage storage) throws EventException {
        String updatedTask = command.replace("event ", "").trim();
        if (updatedTask.isBlank()) {
            throw new EventException("please add a description to your event");
        }
        String eventToAdd = updatedTask.split("/at ")[0].trim();
        String dateOfEvent = updatedTask.split("/at ")[1].trim();

        if (taskList.clashChecker(dateOfEvent)) {
            return ui.displayClashDate(dateOfEvent);
        }

        Event eventTask = new Event(eventToAdd, dateOfEvent);
        taskList.add(eventTask);
        storage.updateHardDisk(taskList);
        return ui.displayAdd(eventTask);
    }
}
