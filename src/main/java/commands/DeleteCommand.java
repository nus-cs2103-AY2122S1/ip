package commands;
import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "delete";

    public DeleteCommand(String userInput) {
        String intString = userInput.substring(KEYWORD.length()).trim();
        this.taskNumber = Integer.parseInt(intString);
    }

    public String execute(TaskList taskList) throws DukeException {
        if (this.taskNumber > taskList.getNumOfTasks()) {
            throw new DukeException("OOPS!!! Please choose a smaller number :-(");
        }
        Task task = taskList.getTask(this.taskNumber);
        taskList.remove(this.taskNumber);
        return "Noted. I've removed this task:\n\t" + task.toString()
                + "\nNow you have " + taskList.getNumOfTasks()
                + " tasks in the list.";
    }
}
