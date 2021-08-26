package Duke.Command;

import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeSyntaxErrorException;
import Duke.Main.TaskList;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;

public class DateOfCommand extends Command {

    private String description;
    private TaskList taskList;
    public DateOfCommand(String description, TaskList taskList) {
        super(description, taskList);
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
            String type = (task instanceof Deadline) ? "Deadline" :
                    (task instanceof Event) ? "Event" : "Todo";
            if (type.equals("Todo")) {
                return task.getDate() + index;
            } else {
                return "The " + type + " time of task " +
                        index + " is " + task.getDate();
            }
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
