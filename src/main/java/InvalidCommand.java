public class InvalidCommand extends Command{
    public InvalidCommand(TaskList taskList) {
        super(taskList);
    }
    
    public void run() {
        Ui.invalidTask();
    }
}
