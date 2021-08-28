package duke.Command;

import duke.*;

public class AddCommand extends Command{
    private String type;
    private String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if(type.equals("todo")) {
            task = new Task.Todo(description, false);

        } else if(type.equals("deadline")) {
            String[] temp = description.split("by ", 2);
            task = new Task.Deadline(temp[0], false, temp[1]);

        } else if(type.equals("event")) {
            String[] temp = description.split("at ", 2);
            task = new Task.Event(temp[0], false, temp[1]);

        } else {
            throw new DukeException("Sorry, I don't understand what that means. :(");
        }

        tasks.addTask(task);
        ui.print("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription());
        ui.print("There are " + tasks.size() + " tasks in the list");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
