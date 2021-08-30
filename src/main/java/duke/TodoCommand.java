package duke;

/**
 *
 * Command to add a Todo Task to the existing list of tasks.
 */
public class TodoCommand extends Command{

    private Task task;

    TodoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
