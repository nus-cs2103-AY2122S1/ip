package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class CompleteCommand extends Command {
    private final int index;

    /**
     * Constructor for a complete command.
     *
     * @param index Integer reference for the task to be deleted.
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        assert index <= tasks.getNumOfTasks() : "invalid task reference";
        assert index > 0 : "reference smaller than 0";

        Task task = tasks.getTask(index);
        if (task.isDone()) {
            return "Great! But you have already completed this task :(";
        } else {
            task.markDone();
            Storage.writeDatabase(tasks);
            return "Nice! I've marked this task as done: \n" + task;
        }
    }
}
