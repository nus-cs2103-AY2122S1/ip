import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    private enum TaskType { ToDo, Event, Deadline, Invalid }
    private static Scanner sc = new Scanner(System.in);
    private static String line = "*･゜ﾟ･*･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*゜ﾟ･*";

    private static String format(String msg) {
        return msg + "\n\n" + line;
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + "." + taskList.get(i - 1));
        }
        System.out.println("\n" + line);
    }

    private static String formatNumTasks() {
        int size = taskList.size();
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }

    private static void doTask(int idx) throws DukeException {
        if (idx >= taskList.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        Task t = taskList.get(idx);
        t.markAsDone();
        System.out.println(format("Nice! I've marked this task as done: \n\t" + t));
    }

    private static void addTask(String[] params) throws DukeException {
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
        taskList.add(t);
        System.out.println(format("Got it. I've added this task: \n\t" + t +
                "\nNow you have " + formatNumTasks() + " in the list."));
    }

    private static void deleteTask(int idx) throws DukeException {
        if (idx >= taskList.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        taskList.remove(idx);
        System.out.println(format("Noted. I've removed this task: \n\t" + taskList.get(idx) +
                "\nNow you have " + formatNumTasks() + " in the list."));
    }

    private static TaskType getTaskType(String taskInput) {
        return taskInput.equals("event") ? TaskType.Event
                : taskInput.equals("deadline") ? TaskType.Deadline
                : taskInput.equals("todo") ? TaskType.ToDo
                : TaskType.Invalid;
    }

    public static void main(String[] args) {
        System.out.println(format("Hello! I'm Duke\nWhat can I do for you?"));
        String[] params = sc.nextLine().split(" ", 2);
        String firstParam = params[0];

        while (!firstParam.equals("bye")) {
            try {
                if (firstParam.equals("list")) {
                    displayTasks();
                } else if (firstParam.equals("done")) {
                    doTask(Integer.parseInt(params[1]) - 1);
                } else if (firstParam.equals("delete")) {
                    deleteTask(Integer.parseInt(params[1]) - 1);
                } else {
                    addTask(params);
                }
            } catch (DukeException e) {
                System.out.println(format(e + "\n\nWhat else can I do for you?"));
            }
            params = sc.nextLine().split(" ", 2);
            firstParam = params[0];
        }
        System.out.println(format("Bye. Hope to see you again soon!"));
    }
}