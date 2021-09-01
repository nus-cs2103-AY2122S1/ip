package duke;

public class AddCommand implements Command {
    private String[] params;

    public AddCommand(String[] params) {
        this.params = params;
    }

    public TaskType getTaskType(String tasktypeInput) {
        return tasktypeInput.equals("event") ? TaskType.Event
                : tasktypeInput.equals("deadline") ? TaskType.Deadline
                : tasktypeInput.equals("todo") ? TaskType.ToDo
                : TaskType.Invalid;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        TaskType type = getTaskType(params[0]);
        if (type.equals(TaskType.Invalid)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (params.length == 1 || params[1].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        Task t;
        String taskInfo = params[1];
        switch (type) {
            case Event:
                String[] eventInfo = taskInfo.split(" /at ");
                if (eventInfo.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please enter event information in the following " +
                            "format:\nevent [event name] /at [yyyy-mm-dd HH:MM]");
                }
                t = new Event(eventInfo[0], eventInfo[1]);
                break;
            case Deadline:
                String[] deadlineInfo = taskInfo.split(" /by ");
                if (deadlineInfo.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please enter deadline information in the following " +
                            "format:\ndeadline [deadline name] /by [yyyy-mm-dd]");
                }
                t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                break;
            case ToDo:
                t = new ToDo(taskInfo);
                break;
            default:
                return;
        }
        taskList.add(t);
        System.out.println(Ui.format("Got it. I've added this task: \n\t" + t +
                "\nNow you have " + ui.formatNumTasks(taskList.size()) + " in the list."));
    }

   public boolean isExit() {
        return false;
    }
}
