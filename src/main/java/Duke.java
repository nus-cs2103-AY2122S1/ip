import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    private static String WELCOME_TEXT = "Hey there I'm Duke!\n" + "How can I help you today?";
    private static String BYE_TEXT = "Bye! Hope to see you again!";

    private Scanner in;
    private List<Task> tasks;
    private boolean shouldExit;

    Duke(Scanner in, List<Task> tasks) {
        this.in = in;
        this.tasks = tasks;
        this.shouldExit = false;
    }

    private void greet() {
        printMessage(WELCOME_TEXT);
    }

    private void handleInput() throws Exception {
        System.out.print("> ");
        String input = this.in.next();

        if (input.equals("bye")) {
            this.shouldExit = true;
            printMessage(BYE_TEXT);
        } else if (input.equals("list")) {
            StringBuilder builder = new StringBuilder();
            int numTasks = this.tasks.size();

            for (int i = 0; i < numTasks; i++) {
                Task item = this.tasks.get(i);
                builder.append(i + 1);
                builder.append(". ");
                builder.append(item.toString());
                if (i < numTasks - 1) {
                    builder.append("\n");
                }
            }

            printMessage(builder.toString());
        } else if (input.equals("done")) {
            int taskIndex = this.in.nextInt();
            Task task = this.tasks.get(taskIndex - 1);
            task.markCompleted();

            printMessage("Marking task as completed:\n    " + task.toString());
        } else if (input.equals("todo")) {
            String todoInput = this.in.nextLine().trim();
            Task todo = Todo.fromInput(todoInput);
            this.tasks.add(todo);

            this.printTaskAddedMessage(todo);
        } else if (input.equals("deadline")) {
            String deadlineInput = this.in.nextLine();
            Task deadline = Deadline.fromInput(deadlineInput);
            this.tasks.add(deadline);

            this.printTaskAddedMessage(deadline);
        } else if (input.equals("event")) {
            String eventInput = this.in.nextLine();
            Task event = Event.fromInput(eventInput);
            this.tasks.add(event);

            this.printTaskAddedMessage(event);
        } else {
            String fullInput = input + " " + this.in.nextLine();
            throw new Exception("Command not recognized: " + fullInput);
        }
    }

    private void printTaskAddedMessage(Task task) {
        printMessage("Added the following task:\n    " + task.toString() + "\n" + "You now have " + this.tasks.size()
                + " tasks in your list.");
    }

    private boolean shouldExit() {
        return this.shouldExit;
    }

    private void shutdown() {
        in.close();
    }

    private static void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Duke duke = new Duke(input, new ArrayList<>());

        duke.greet();
        while (!duke.shouldExit()) {
            try {
                duke.handleInput();
            }
            // TODO: custom Duke exceptions?
            catch (Exception e) {
                printMessage("Error: " + e.getMessage());
            }
        }
        duke.shutdown();
    }
}
