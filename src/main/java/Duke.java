
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    print("Bye. Hope to see you again soon!");
                    return;
                } else if (input.equals("list")) {
                    print(list());
                } else if (input.startsWith("done")) {
                    print(done(input));
                } else if (input.startsWith("delete")) {
                    print(delete(input));
                } else {
                    print(addTask(input));
                }
            } catch (Exception e) {
                print(e.getMessage());
            }
        }
    }

    static void greet() {
        print("Hello! I'm Duke", "What can I do for you?");
    }

    static String[] list() {
        List<String> out = new ArrayList<>();
        out.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            out.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        return out.toArray(new String[0]);
    }

    static String[] done(String input) {
        int ind = Integer.parseInt(input.split(" ", 2)[1]);
        Task task = tasks.get(ind - 1);
        task.setDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + task};
    }

    static String[] addTask(String input) throws DukeException {
        Task task = makeTask(input);
        tasks.add(task);
        return new String[]{
                "Got it. I've added this task:", "  " + task, getTasksLeftMsg()};
    }

    static Task makeTask(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String type = parts[0];
        switch (type) {
            case "todo":
                if (parts.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return new Todo(parts[1]);
            case "deadline": {
                String[] subparts = parts[1].split(" /by ", 2);
                return new Deadline(subparts[0], subparts[1]);
            }
            case "event": {
                String[] subparts = parts[1].split(" /at ", 2);
                return new Event(subparts[0], subparts[1]);
            }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    static String[] delete(String input) {
        int ind = Integer.parseInt(input.split(" ", 2)[1]);
        Task task = tasks.remove(ind - 1);
        return new String[]{"Noted. I've removed this task:", "  " + task, getTasksLeftMsg()};
    }

    static String getTasksLeftMsg() {
        return String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    static void print(String... lines) {
        printSeparator();
        for (String line : lines)
            println(line);
        printSeparator();
    }

    static void printSeparator() {
        System.out.println("\t____________________________________");
    }

    static void println(String out) {
        System.out.println("\t " + out);
    }

}
