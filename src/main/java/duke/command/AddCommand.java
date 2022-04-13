package duke.command;

import duke.tasks.TaskManager;
import duke.tasks.TaskType;

public class AddCommand extends Command{
    public TaskType type;


    public AddCommand(TaskManager taskList, String arguments, TaskType type){
        super(taskList, arguments);
        this.type = type;
    }

    @Override
    public CommandResult execute() throws Exception {

        String message = "";

        if (type == TaskType.TODO) {
            message = taskList.addTodo(arguments);

        } else if (type == TaskType.DEADLINE) {
            message = taskList.addDeadline(arguments);

        } else if (type == TaskType.EVENT) {
            message = taskList.addEvent(arguments);
        }

        return new CommandResult(message, true, taskList);
    }
}
