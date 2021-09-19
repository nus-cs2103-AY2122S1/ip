package chad.command;

import chad.task.Task;
import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a generic "Add Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class AddTaskCommand extends Command implements UndoableCommand {

    private static final String ADD_TASK_SUCCESSFUL_MESSAGE = "Got it. I've added this task:";
    private static final String MISSING_TASK_DESCRIPTION_ERROR_TEMPLATE =
            "A description is required for \"%s\" commands.";

    private Task task;
    private String taskDescription;
    private int taskIndex;

    /**
     * Creates an AddTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddTaskCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    /**
     * Creates the task relevant to the "Add Task" command.
     *
     * @return The task created.
     */
    abstract Task createTask();

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        assert task != null : "Task to be added must be created; it cannot be null.";
        taskHandler.addTask(task);
        int numberOfTasks = taskHandler.getNumberOfTasks();
        ui.startMessage()
                .addLine(ADD_TASK_SUCCESSFUL_MESSAGE)
                .addTask(task)
                .addTasksListLength(numberOfTasks)
                .displayMessage();
        taskIndex = numberOfTasks - 1;
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.addToUndoableCommands(this);
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        String taskDescription = parseTaskDescription(tokens);
        checkTaskDescriptionLength(taskDescription);
        setTaskDescription(taskDescription);
        setTask(createTask());
    }

    @Override
    public void undo(TaskHandler taskHandler) {
        taskHandler.deleteTask(taskIndex);
    }

    void setTask(Task task) {
        this.task = task;
    }

    void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    String getTaskDescription() {
        return this.taskDescription;
    }

    private String parseTaskDescription(String[] tokens) {
        return getTokenSequence(tokens, 1, tokens.length);
    }

    void checkTaskDescriptionLength(String taskDescription) throws ChadInvalidCommandException {
        if (taskDescription.length() == 0) {
            throw new ChadInvalidCommandException(String.format(MISSING_TASK_DESCRIPTION_ERROR_TEMPLATE,
                    getCommandType().getCommandDescription()));
        }
    }
}
