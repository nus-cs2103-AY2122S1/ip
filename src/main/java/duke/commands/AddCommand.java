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

    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
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
                String updatedTask = command.replace("todo", "").trim();
                if (updatedTask.isBlank()) {
                    throw new ToDoException("please add a description to your todo task");
                }
                ToDo toDoTask = new ToDo(updatedTask);
                taskList.add(toDoTask);
                storage.updateHardDisk(taskList);
                return ui.displayAdd(toDoTask);


            } else if (command.contains("deadline")) {

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

            } else if (command.contains("event")) {
                String updatedTask = command.replace("event ", "").trim();
                if (updatedTask.isBlank()) {
                    throw new EventException("please add a description to your event");
                }
                String eventToAdd = updatedTask.split("/at ")[0].trim();
                String dateOfEvent = updatedTask.split("/at ")[1].trim();

                Event eventTask = new Event(eventToAdd, dateOfEvent);
                taskList.add(eventTask);
                storage.updateHardDisk(taskList);
                return ui.displayAdd(eventTask);
            }

        } catch (DukeException e) {
            return ui.showError(e);
        }
        return "";
    }
}
