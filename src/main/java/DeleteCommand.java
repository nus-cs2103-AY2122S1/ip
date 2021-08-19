public class DeleteCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "delete";

    public DeleteCommand(String userInput) {
        String intString = userInput.substring(KEYWORD.length()).trim();
        this.taskNumber = Integer.parseInt(intString);
    }

    public String execute(TaskManager taskManager) throws DukeException {
        if (this.taskNumber > taskManager.getNumOfTasks()) {
            throw new DukeException("OOPS!!! Please choose a smaller number :-(");
        }
        Task task = taskManager.getTask(this.taskNumber);
        taskManager.remove(this.taskNumber);
        return "Noted. I've removed this task:\n\t" + task.toString()
                + "\nNow you have " + taskManager.getNumOfTasks()
                + " tasks in the list.";
    }
}
