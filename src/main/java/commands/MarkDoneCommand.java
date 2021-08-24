package commands;
import tasks.Task;
import tasks.TaskList;
import exceptions.DukeException;

public class MarkDoneCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "done";

    public MarkDoneCommand(String userInput) throws DukeException {
        String intString = userInput.substring(KEYWORD.length()).trim();
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please provide a valid task number!");
        }
    }

    public String execute(TaskList taskList) throws DukeException {
        boolean isInputValid = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;
        if (!isInputValid) {
            throw new DukeException("OOPS!!! Please choose a valid task number!");
        }
        taskList.markAsDone(this.taskNumber);
        Task task = taskList.getTask(this.taskNumber);
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
