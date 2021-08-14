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
                list();
            } else {
                tasks.add(new Task(input));
                print("added: " + input);
            }
        }
    }

    static void greet() {
        print("Hello! I'm Duke", "What can I do for you?");
    }

    static void list() {
        List<String> out = new ArrayList<>();
        out.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            out.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        print(out.toArray(new String[0]));
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
