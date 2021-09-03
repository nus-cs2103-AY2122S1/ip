package winston;

/**
 * Represents the deadline command from the parent abstract class Command.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String dueDate;
    
    public AddDeadlineCommand(TaskList taskList, String description, String dueDate) {
        super(taskList);
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Adds a 'deadline' to the TaskList and prints the number of uncompleted tasks remaining
     */
    @Override
    public String run() {
        super.taskList.addTask(new DeadLine(description, dueDate));
        String result = Ui.printTasksLeft(taskList.numberOfIncompleteTasks());
        assert(!result.equals(""));
        return result;
    }
}
