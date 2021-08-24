package commands;
import tasks.Task;
import tasks.TaskList;
import exceptions.DukeException;

public class MarkDoneCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "done";

    public MarkDoneCommand(String userInput) {
        String intString = userInput.substring(KEYWORD.length()).trim();
        this.taskNumber = Integer.parseInt(intString);
    }

    public String execute(TaskList taskList) throws DukeException {
        boolean isInputValid = this.taskNumber <= taskList.getNumOfTasks();
        if (!isInputValid) {
            throw new DukeException("OOPS!!! Please choose a smaller number!");
        }
        taskList.markAsDone(this.taskNumber);
        Task task = taskList.getTask(this.taskNumber);
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
