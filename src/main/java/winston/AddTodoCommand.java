package winston;

/**
 *  Represents the event command from the parent abstract class Command.
 */
public class AddTodoCommand extends Command {
    private String description;
    
    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    /**
     * Adds a 'todoTask' to the TaskList and prints the number of uncompleted tasks remaining
     */
    @Override
    public void run() {
        super.taskList.addTask(new ToDoTask(this.description));
        Ui.printTasksLeft(taskList.uncompletedTasks());
    }
}
