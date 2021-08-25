package duke.command;

import duke.TaskList;
import duke.CommandResult;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command implements TaskListAddable {

    private final String command;
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(TaskList taskList, String command) {
        super(taskList);
        this.command = command;
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        String[] eventList = this.command.split(" /by ");
        if (eventList.length != 2) {
            throw new DukeException("Incorrect command was given for deadline. " + "Try this: deadline name_here" +
                    " /at date_here");
        }
        Task event = new Deadline(eventList[0], eventList[1], false);
        String feedback = addTaskToTaskList(taskList, event);
        return new CommandResult(feedback, false);
    }

    @Override
    public String addTaskToTaskList(TaskList taskList, Task task) {
        taskList.addTask(task);
        return "Got it. I've added this task:\n  "
                + task.getDetails()
                + "\n"
                + printListNumber(taskList);
    }
}
