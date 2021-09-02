package duke.command;

import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeSyntaxErrorException;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class DateOfCommand extends Command {

    private final String description;
    private final TaskList taskList;

    /**
     * Constructor for the DateOfCommand class
     * @param description description of command
     * @param taskList task list to be modified
     */
    public DateOfCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        if (description.length() == 0) {
            throw new DukeIncompleteException();
        }
        try {
            int index = Integer.parseInt(description);
            Task task = taskList.get(index);
            String type = (task instanceof Deadline) ? "Deadline"
                        : (task instanceof Event) ? "Event" : "Todo";
            if (type.equals("Todo")) {
                return task.getDate() + index;
            } else {
                return "The " + type + " time of task "
                        + index + " is " + task.getDate();
            }
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
