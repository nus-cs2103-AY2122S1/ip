package duke.command;

import duke.task.Task;
import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a generic "Add Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class AddTaskCommand extends Command {

    private Task task;
    private String taskDescription;

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
        taskHandler.addTask(task);
        ui.startMessage()
                .addLine("Got it. I've added this task:")
                .addTask(task)
                .addTasksListLength(taskHandler.getNumberOfTasks())
                .printFormatted();
    }

    @Override
    void parseCommand(String[] tokens) {
        StringBuilder taskDescriptionSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskDescriptionSb.append(tokens[i]).append(" ");
        }
        String taskDescription = taskDescriptionSb.toString().strip();
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A description is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
        setTaskDescription(taskDescription);
        setTask(createTask());
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
}
