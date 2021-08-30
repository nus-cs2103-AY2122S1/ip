package duke;

/**
 * 
 * Command to delete a task from existing list of tasks.
 */
public class DeleteCommand extends Command{

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n" + taskRef);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
