package duke.command;

import duke.task.Task;
import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a generic "Add Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class AddTaskCommand extends Command implements UndoableCommand {

    private Task task;
    private String taskDescription;
    private int taskIndex;

    /**
     * Creates an AddTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddTaskCommand(String command) {
        super(command);
    }

    /**
     * Creates the task relevant to the "Add Task" command.
     *
     * @return The task created.
     */
    abstract Task createTask();

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        assert task != null : "Task to be added must be created; it cannot be null.";
        taskHandler.addTask(task);
        int numberOfTasks = taskHandler.getNumberOfTasks();
        ui.startMessage()
                .addLine("Got it. I've added this task:")
                .addTask(task)
                .addTasksListLength(numberOfTasks)
                .printFormatted();
        taskIndex = numberOfTasks - 1;
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.addToUndoableCommands(this);
    }

    @Override
    void parseCommand(String[] tokens) {
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

    private void checkTaskDescriptionLength(String taskDescription) {
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A description is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
    }
}
