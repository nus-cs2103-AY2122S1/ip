import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                print(list());
            } else if (input.startsWith("done")) {
                print(done(Integer.parseInt(input.split(" ", 2)[1])));
            } else {
                print(addTask(input));
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

    static String[] done(int ind) {
        Task task = tasks.get(ind - 1);
        task.setDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + task};
    }

    static String[] addTask(String input) {
        String[] parts = input.split(" ", 2);
        String type = parts[0];
        Task task = null;
        if (type.equals("todo")) {
            task = new Todo(parts[1]);
        }
        tasks.add(task);
        return new String[]{
                "Got it. I've added this task:",
                "  " + task.toString(),
                String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks")};
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
