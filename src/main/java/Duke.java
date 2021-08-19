import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    private static String WELCOME_TEXT = "Hey there I'm Duke!\n" + "How can I help you today?";
    private static String BYE_TEXT = "Bye! Hope to see you again!";

    private static void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        printMessage(WELCOME_TEXT);

        while (true) {
            System.out.print("> ");
            String input = in.next();

            if (input.equals("bye")) {
                printMessage(BYE_TEXT);
                break;
            } else if (input.equals("list")) {
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < tasks.size(); i++) {
                    Task item = tasks.get(i);
                    builder.append(i + 1);
                    builder.append(". ");
                    builder.append(item.toString());
                    if (i < tasks.size() - 1) {
                        builder.append("\n");
                    }
                }

                printMessage(builder.toString());
            } else if (input.equals("done")) {
                int taskIndex = in.nextInt();
                Task task = tasks.get(taskIndex - 1);
                task.markCompleted();

                printMessage("Marking task as completed:\n    " + task.toString());
            } else if (input.equals("todo")) {
                String todoInput = in.nextLine().trim();
                Task todo = new Todo(todoInput);
                tasks.add(todo);

                printMessage("Added the following task:\n    " + todo.toString() + "\n" + "You now have " + tasks.size()
                        + " tasks in your list.");
            } else if (input.equals("deadline")) {
                String[] deadlineInputs = in.nextLine().trim().split("\\s+/by\\s+", 2);
                Task deadline = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                tasks.add(deadline);

                printMessage("Added the following task:\n    " + deadline.toString() + "\n" + "You now have " + tasks.size()
                        + " tasks in your list.");
            } else if (input.equals("event")) {
                String[] eventInputs = in.nextLine().trim().split("\\s+/at\\s+", 2);
                Task event = new Event(eventInputs[0], eventInputs[1]);
                tasks.add(event);

                printMessage("Added the following task:\n    " + event.toString() + "\n" + "You now have " + tasks.size()
                        + " tasks in your list.");
            } else {
                printMessage(input);
            }
        }

        in.close();
    }
}
