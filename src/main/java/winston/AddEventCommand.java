package winston;

public class AddEventCommand extends Command{
    private String dateAndTime;
    private String description;
    
    public AddEventCommand(TaskList taskList, String description, String dateAndTime) {
        super(taskList);
        this.description = description;
        this.dateAndTime = dateAndTime;
    }
    
    @Override
    public void run() {
        super.taskList.addTask(new Event(this.description, this.dateAndTime));
        Ui.printTasksLeft(taskList.completedTasks());
    }
}
