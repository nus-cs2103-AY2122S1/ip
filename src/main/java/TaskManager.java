import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public String taskDescription(String input) throws DukeException.MissingDescriptionException {
        String[] description = input.split(" ", 2);
        if (description.length == 1) {
            throw new DukeException.MissingDescriptionException();
        }
        return description[1];
    }

    public void addTask(String task, String cmd) {
        Task t;
        if (cmd.equals("todo")) {
            t = new Todo(task);
            tasks.add(t);
        } else if (cmd.equals("deadline")) {
            String date = task.split(" /by ")[1];
            task = task.split(" /by ")[0];
            t = new Deadline(task, date);
            tasks.add(t);
        } else {
            String date = task.split(" /at ")[1];
            task = task.split(" /at ")[0];
            t = new Event(task, date);
            tasks.add(t);
        }
        Message.add(t);
    }

    public void deleteTask(int taskId) throws DukeException.MissingTaskException {
        try {
            Task t = tasks.get(taskId-1);
            tasks.remove(t);
            --Task.totalTasks;
            Message.delete(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.MissingTaskException();
        }
    }

    public void markDone(int taskId) throws DukeException.MissingTaskException {
        try {
            Task t = tasks.get(taskId-1);
            t.isDone = true;
            Message.done(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.MissingTaskException();
        }
    }

    public void run() {
        Message.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2);
            String command = words[0];
            try {
                switch (command) {
                    case "list":
                        Message.list(tasks);
                        break;
                    case "done":
                        markDone(Integer.parseInt(taskDescription(input)));
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(taskDescription(input), command);
                        break;
                    case "delete":
                        deleteTask(Integer.parseInt(taskDescription(input)));
                        break;
                    default:
                        throw new DukeException.InvalidInputException();
                }
            } catch (DukeException e) {
                Message.error(e.toString());
            } finally {
                input = sc.nextLine();
            }
        }
        Message.exit();
    }
}
