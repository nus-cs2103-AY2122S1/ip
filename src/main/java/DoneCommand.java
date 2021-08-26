import java.io.PrintWriter;

public class DoneCommand extends Command {
    String taskNumber;
    public DoneCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Integer count = Integer.valueOf(this.taskNumber);
        tasks.getTask(count - 1).markAsDone();
        ui.respondToDone(tasks.getTasks(), count);
        storage.rewriteFile(tasks.getTasks());
        //System.out.println("hi");;
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
