package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class AddToDoCommand extends Command {
    private String command;

    public AddToDoCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        if (command.length() < 6 || command == null) {
            throw new DukeException("invalidToDo");
        }
        String name = command.substring(5);
        Task task = new ToDo(name);
        tasks.add(task);
        try {
            storage.appendToFile("data/duke.txt", "T - 0 - " + name);
        } catch (IOException e) {
            return new String[] {e.toString()};
        }
        return Ui.printTaskAdded(task, tasks.size());

    }
}