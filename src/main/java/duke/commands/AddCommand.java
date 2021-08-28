package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DeadLineException;
import duke.exceptions.DukeException;
import duke.exceptions.EventException;
import duke.exceptions.ToDoException;
import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.TaskList;
import duke.tasktypes.ToDos;


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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (command.contains("todo")) {
                String updatedTask = command.replace("todo", "").trim();
                if (updatedTask.isBlank()) {
                    throw new ToDoException("please add a description to your todo task");
                }
                ToDos toDoTask = new ToDos(updatedTask);
                taskList.add(toDoTask);
                ui.displayAdd(toDoTask);

            } else if (command.contains("deadline")) {

                String updatedTask = command.replace("deadline", "").trim();
                if (updatedTask.isBlank()) {
                    throw new DeadLineException("please add a description to your deadline task");
                }
                String deadlineToAdd = updatedTask.split("/by ")[0].trim();
                String finishBy = updatedTask.split("/by ")[1].trim();

                Deadlines deadlineTask = new Deadlines(deadlineToAdd, finishBy);
                taskList.add(deadlineTask);
                ui.displayAdd(deadlineTask);

            } else if (command.contains("event")) {
                String updatedTask = command.replace("event ", "").trim();
                if (updatedTask.isBlank()) {
                    throw new EventException("please add a description to your event");
                }
                String eventToAdd = updatedTask.split("/at ")[0].trim();
                String dateOfEvent = updatedTask.split("/at ")[1].trim();

                Events eventTask = new Events(eventToAdd, dateOfEvent);
                taskList.add(eventTask);
                ui.displayAdd(eventTask);
            }

            storage.updateHardDisk(taskList);

        } catch (DukeException e) {
            ui.showError(e);
        }

    }
}
