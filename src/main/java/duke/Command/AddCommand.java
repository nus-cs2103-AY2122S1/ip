package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    private String type;
    private String description;

    /**
     * Constructor for AddCommand
     * @param type Type of task
     * @param description Description of task
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (type.equals("todo")) {
            task = new Task.Todo(description, false);

        } else if (type.equals("deadline")) {
            String[] temp = description.split("by ", 2);
            task = new Task.Deadline(temp[0], false, temp[1]);

        } else if (type.equals("event")) {
            String[] temp = description.split("at ", 2);
            task = new Task.Event(temp[0], false, temp[1]);

        } else {
            throw new DukeException("Sorry, I don't understand what that means. :(");
        }

        tasks.addTask(task);
        String result = "";
        result += ("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription() + "\n");
        result += ("There are " + tasks.size() + " tasks in the list");
        storage.save(tasks);
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
