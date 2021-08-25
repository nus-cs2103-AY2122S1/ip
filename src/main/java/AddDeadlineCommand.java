public class AddDeadlineCommand extends Command{
    private String description;
    private String dueDate;
    
    public AddDeadlineCommand(TaskList taskList, String description, String dueDate) {
        super(taskList);
        this.description = description;
        this.dueDate = dueDate;
    }
    
    @Override
    public void run() {
        super.taskList.addTask(new DeadLine(description, dueDate));
        Ui.printTasksLeft(taskList.size());
    }
}
