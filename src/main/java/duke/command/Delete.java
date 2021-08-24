package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;
import duke.task.Task;

public class Delete extends Command {
    private int index;

    public Delete(int index) {
        this.index = index;
    }

    public void exec(TaskList tasks, Ui ui, Storage storage) throws NoListException {
        Task temp = tasks.get(index - 1);
        tasks.delete(index);
        try {
            storage.save(tasks);
            System.out.println("Noted. I've removed this task:\n"
                    + temp.toString() + "\n"

                    + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (NoListException e) {
            throw new NoListException(e);
        }
    }
}