import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> existingTasks) {
        this.tasks = existingTasks;
    }

    private String formatNumTasks() {
        int size = tasks.size();
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }

    private Duke.TaskType getTaskType(String taskInput) {
        return taskInput.equals("event") ? Duke.TaskType.Event
                : taskInput.equals("deadline") ? Duke.TaskType.Deadline
                : taskInput.equals("todo") ? Duke.TaskType.ToDo
                : Duke.TaskType.Invalid;
    }

    public void handleDisplayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    public void handleDoneTask(int idx) throws DukeException {
        if (idx >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        Task t = tasks.get(idx);
        t.markAsDone();
        System.out.println(("Nice! I've marked this task as done: \n\t" + t));
    }

    public void handleAddTask(String[] params) throws DukeException {
        Duke.TaskType type = getTaskType(params[0]);
        if (type.equals(Duke.TaskType.Invalid)) {
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
                            "format:\nevent [event name] \\at [date and time]");
                }
                t = new Event(eventInfo[0], eventInfo[1]);
                break;
            case Deadline:
                String[] deadlineInfo = taskInfo.split(" /by ");
                if (deadlineInfo.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please enter deadline information in the following " +
                            "format:\ndeadline [deadline name] \\by [date and time]");
                }
                t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                break;
            case ToDo:
                t = new ToDo(taskInfo);
                break;
            default:
                return;
        }
        tasks.add(t);
        System.out.println(Ui.format("Got it. I've added this task: \n\t" + t +
                "\nNow you have " + formatNumTasks() + " in the list."));
    }

    public void handleDeleteTask(int idx) throws DukeException {
        if (idx >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        tasks.remove(idx);
        System.out.println(Ui.format("Noted. I've removed this task: \n\t" + tasks.get(idx) +
                "\nNow you have " + formatNumTasks() + " in the list."));
    }
}
