public class MarkDoneCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "done";

    public MarkDoneCommand(String userInput) {
        String intString = userInput.substring(KEYWORD.length()).trim();
        this.taskNumber = Integer.parseInt(intString);
    }

    public String execute(TaskManager taskManager) throws DukeException {
        if (this.taskNumber > taskManager.getNumOfTasks()) {
            throw new DukeException("OOPS!!! Please choose a smaller number :-(");
        }
        taskManager.markAsDone(this.taskNumber);
        Task task = taskManager.getTask(this.taskNumber);
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
