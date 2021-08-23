import java.io.IOException;
import java.util.Scanner;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (tasks.taskList.size() < this.taskNum) {
            throw new DukeException("You cannot complete a task that does not exist!");
        } else {
            Scanner newSc = new Scanner(storage.filePath);
            storage.markAsDoneInFile(taskNum, newSc, tasks);
            completeTask(tasks.taskList.get(taskNum - 1));
        }
    };

    public boolean isExit() {
        return false;
    }

    /**
     * Mark a task's statis as done and print out the result.
     * 
     * @param task the task to be marked as done
     */
    public void completeTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        task.markAsDone();
        task.showThisTask();
    }
}
