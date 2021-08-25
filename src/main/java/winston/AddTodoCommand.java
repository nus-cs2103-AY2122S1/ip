package winston;
public class AddTodoCommand extends Command {
    private final String description;
    
    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }
    
    @Override
    public void run() {
        super.taskList.addTask(new ToDoTask(this.description));
        Ui.printTasksLeft(taskList.completedTasks());
    }
}
