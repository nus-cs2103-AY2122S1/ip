package duke;

import java.util.List;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        if (this.index > savedTasks.size() || this.index < 1) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            Task oldTask = savedTasks.get(this.index-1);
            Task newTask = oldTask.setDone();
            savedTasks.set(this.index-1, newTask);
            System.out.println("Nice! I've marked this task as done:\n" + newTask);
        }
    }
}
