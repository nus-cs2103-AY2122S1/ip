import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static List<Task> tasks = new ArrayList<>();

    private static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void add(String string) {
        tasks.add(new Task(string));
        printReply("added: " + string);
    }

    private static void list() {
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            Task currentTask = tasks.get(i);
            tasksBuilder.append(counter).append(".").append(currentTask);
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        printReply(tasksBuilder.toString());
    }

    public static void done(int counter) {
        if (counter <= 0 || counter > tasks.size()) {
            printReply("Sorry, no such task found.");
            return;
        }
        Task doneTask = tasks.get(counter - 1);
        doneTask.markAsDone();
        printReply("Nice! I've marked this task as done:\n\t" + doneTask);
    }

    public static void main(String[] args) {
        greet();
        String readIn;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                readIn = scanner.nextLine();
                if (readIn.equals(EXIT_COMMAND)) {
                    break;
                } else if (readIn.equals(LIST_COMMAND)) {
                    list();
                } else if (readIn.startsWith("done")) {
                    String[] splitCommand = readIn.split(" ");
                    int counter = Integer.parseInt(splitCommand[1]);
                    done(counter);
                } else {
                    add(readIn);
                }
            } catch (Exception e) {
                printReply("ERROR: " + e.getMessage());
            }
        }
        printReply("Bye. Hope to see you again soon!");
    }
}
