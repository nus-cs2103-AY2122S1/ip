package winston;

/**
 * Represents the event command from the parent abstract class Command.
 */
public class AddTodoCommand extends Command {
    private final String description;
    
    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    /**
     * Adds a 'toDoTask' to the TaskList and prints the number of uncompleted tasks remaining
     */
    @Override
    public String run() {
        super.taskList.addTask(new ToDoTask(this.description));
        String result =  Ui.printTasksLeft(taskList.numberOfIncompleteTasks());
        assert(!result.equals(""));
        return result;
    }
}
