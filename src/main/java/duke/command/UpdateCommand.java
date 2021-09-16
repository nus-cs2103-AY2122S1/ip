package duke.command;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucSyntaxErrorException;
import duke.main.TaskList;
import duke.task.Task;

public class UpdateCommand extends Command {
    private static final String ACKNOWLEDGEMENT = "Deal, I have updated the task at position ";
    private final String description;
    private final TaskList taskList;

    /**
     * Constructor for UpdateCommand type
     * @param description update description
     * @param taskList task list to be modified
     */
    public UpdateCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        String[] commandComponents = this.description.split(" ", 3);
        if (commandComponents.length < 3) {
            throw new DucIncompleteException();
        }
        String indexString = commandComponents[0];
        String taskType = commandComponents[1];
        String taskInfo = commandComponents[2];
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DucSyntaxErrorException(indexString);
        }
        Task newTask;
        switch (taskType.toLowerCase()) {
        case "todo":
            newTask = taskList.replaceTask(taskInfo, Task.Type.TODO, index);
            break;
        case "deadline":
            newTask = taskList.replaceTask(taskInfo, Task.Type.DEADLINE, index);
            break;
        case "event":
            newTask = taskList.replaceTask(taskInfo, Task.Type.EVENT, index);
            break;
        default:
            throw new DucSyntaxErrorException(taskType);
        }
        return ACKNOWLEDGEMENT + index + " as:\n" + newTask;
    }
}
