import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private static TaskList taskList = new TaskList();

    private static void divider() {
        String indent = "    ";
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));
        String line = indent + '+' + builder.toString() + "+\n";
        System.out.println(line);
    }

    private static void greet() {
        String indent = "    ";
        String greeting = indent + "Hello! I'm Duke\n"
                + indent+ "What can I do for you?";

        divider();
        System.out.println(greeting);
        divider();
    }

    private static void exit() {
        String indent = "    ";
        String exitMessage = indent + "Bye. Hope to see you again soon!";

        divider();
        System.out.println(exitMessage);
        divider();
    }

    private static void returnTaskList() {
        divider();
        System.out.println(taskList);
        divider();
    }

    private static void updateTaskList(String command) {
        divider();
        Duke.taskList = taskList.add(command);
        divider();
    }

    public static void main(String[] args) {
        // Greeting the user
        greet();

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine().strip();
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                Duke.returnTaskList();
            } else {
                // Add the task to the task list
                Duke.updateTaskList(command);
            }
        }

    }
}
