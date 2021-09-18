package duke;

/**
 * CommandHandler manages all the commands the user can input.
 * It is an intermediary between the user inputs and the output UI messages.
 */
public class CommandHandler {
    protected Ui ui;
    protected TaskList taskList;

    /**
     * Constructor for the command handler.
     *
     * @param ui UI
     * @param taskList taskList
     */
    public CommandHandler(Ui ui, TaskList taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Handles an event task command.
     *
     * @param eventTask The event task after the user's string was parsed.
     * @return A message to the UI after handling the event task.
     */
    public String handleEvent(Task eventTask) {
        taskList.add(eventTask);
        return ui.addTask(eventTask);
    }

    /**
     * Handles a deadline task command.
     *
     * @param deadlineTask The deadline task after the user's string was parsed.
     * @return A message to the UI after handling the deadline task.
     */
    public String handleDeadline(Task deadlineTask) {
        taskList.add(deadlineTask);
        return ui.addTask(deadlineTask);
    }

    /**
     * Handles a todo task command.
     *
     * @param toDoTask The todo task after the user's string was parsed.
     * @return A message to the UI after handling the todo task.
     */
    public String handleTodo(Task toDoTask) {
        taskList.add(toDoTask);
        return ui.addTask(toDoTask);
    }

    /**
     * Handles the find command.
     *
     * @param input The keyword specified to find certain tasks.
     * @return A message to the UI with all the tasks that contain the keyword specified.
     * @throws EmptyDescriptionException If the keyword is not specified.
     */
    public String handleFind(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String keyword = input.substring(5);
        return ui.findTask(keyword);
    }

    /**
     * Handles the delete command.
     *
     * @param input The task number to be deleted.
     * @return A message to the UI after deleting the task.
     * @throws EmptyDescriptionException If the task number is not specified.
     */
    public String handleDelete(String input) throws EmptyDescriptionException {
        if (input.length() == 6) {
            throw new EmptyDescriptionException("error");
        }
        char taskIndex = input.charAt(7);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = taskList.getTask(index);

        taskList.removeTask(index);
        return ui.deleteTask(taskAtIndex);
    }

    /**
     * Handles the done command.
     *
     * @param input The task number to be marked as done.
     * @return A message to the UI after marking the task as done.
     * @throws EmptyDescriptionException If the task number is not specified.
     */
    public String handleDone(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        char indexOfTask = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(indexOfTask));

        Task taskAtIndex = taskList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

}
