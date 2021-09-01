package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

public class AddCommand implements Command {
    private String[] params;

    public AddCommand(String[] params) {
        this.params = params;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        if (params.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        Task t;
        String taskType = params[0];
        String taskInfo = params[1];
        System.out.println(taskInfo);
        switch (taskType) {
        case "event":
            String[] eventInfo = taskInfo.split(" /at ");
            if (eventInfo.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter event information in the following " +
                        "format:\nevent [event name] /at [yyyy-mm-dd HH:MM]");
            }
            t = new Event(eventInfo[0], eventInfo[1]);
            break;
        case "deadline":
            String[] deadlineInfo = taskInfo.split(" /by ");
            if (deadlineInfo.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter deadline information in the following " +
                        "format:\ndeadline [deadline name] /by [yyyy-mm-dd]");
            }
            t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            break;
        case "todo":
            t = new ToDo(taskInfo);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        taskList.add(t);
        System.out.println(Ui.format("Got it. I've added this task: \n\t" + t +
                "\nNow you have " + ui.formatNumTasks(taskList.size()) + " in the list."));
    }

   public boolean isExit() {
        return false;
    }
}
