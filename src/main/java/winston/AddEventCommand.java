package winston;

/**
 *  Represents the event command from the parent abstract class Command.
 */
public class AddEventCommand extends Command{
    private String dateAndTime;
    private String description;
    
    public AddEventCommand(TaskList taskList, String description, String dateAndTime) {
        super(taskList);
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Adds an 'event' to the TaskList and prints the number of uncompleted tasks remaining
     */
    @Override
    public void run() {
        super.taskList.addTask(new Event(this.description, this.dateAndTime));
        Ui.printTasksLeft(taskList.uncompletedTasks());
    }
}
