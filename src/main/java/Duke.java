import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static String doneCommand = "Nice! I've marked this task as done:";

    private static void divider() {
        String indent = "    ";
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));

        String line = String.format("%4s+%s+\n", " ", builder.toString());
        System.out.println(line);
    }

    private static void greet() {
        String intro = "Hello! I'm Duke";
        String greeting = "What can I do for you?";

        divider();
        System.out.println(
                String.format("%4s%s\n%4s%s", " ", intro, " ", greeting)
        );
        divider();
    }

    private static void exit() {
        String exitMessage ="Bye. Hope to see you again soon!";

        divider();
        System.out.println(String.format("%4s", exitMessage));
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
        System.out.println(String.format("%4sadded: %s", " ", command));
        divider();
    }

    private static void markTaskAsCompleted(int index) {
        boolean isValid = taskList.isValidTaskIndex(index);
        divider();
        if (isValid) {
            Task task = taskList.markTaskAsCompleted(index);
            System.out.println(
                    String.format("%4s%s\n%6s%s", " ", doneCommand, " ", task)
            );
        } else {
            System.out.println(String.format("%4s%s", " ", "There is no such task."));
        }
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
            } else if (command.startsWith("done")) {
                try {
                    int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                    Duke.markTaskAsCompleted(taskIndex);
                } catch (NumberFormatException e){
                    System.out.println(e);
                } finally {

                }
            } else {
                // Add the task to the task list
                Duke.updateTaskList(command);
            }
        }

    }
}
