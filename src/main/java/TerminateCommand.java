public class TerminateCommand extends Command {
    private static boolean isTerminated = false;
    
    public TerminateCommand(TaskList taskList) {
        super(taskList);
        isTerminated = true;
    }
    
    public void run() {
        Ui.terminationMessage();
    }
    
    public static boolean isExit() {
        return isTerminated;
    }
}
