import java.io.IOException;
import java.util.Scanner;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (tasks.taskList.size() < this.taskNum) {
            throw new DukeException("You cannot complete a task that does not exist!");
        } else {
            Scanner newSc = new Scanner(storage.filePath);
            storage.deleteTaskFromFile(taskNum, newSc, tasks);
            Task taskToDelete = tasks.taskList.get(taskNum - 1);
            tasks.taskList.remove(taskToDelete);
            System.out.println("Noted. I've removed this task: ");
            taskToDelete.showThisTask();
            String taskform;
            if (tasks.taskList.size() == 1 || tasks.taskList.size() == 0) {
                taskform = " task";
            } else {
                taskform = " tasks";
            }
            System.out.println("Now you have " + tasks.taskList.size() + taskform + " in the list.");
        }
    };

    public boolean isExit() {
        return false;
    }
}
