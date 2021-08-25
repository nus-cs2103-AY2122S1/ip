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
        String command = this.in.next();
        String arguments = this.in.nextLine().trim();
        boolean hasArguments = arguments.length() != 0;

        switch (command) {
            case "bye": {
                if (hasArguments) {
                    throw new Exception("Command `bye` does not accept arguments");
                }

                this.shouldExit = true;
                printMessage(BYE_TEXT);
                break;
            }
            case "list": {
                if (hasArguments) {
                    throw new Exception("Command `list` does not accept arguments");
                }

                StringBuilder builder = new StringBuilder();
                int numTasks = this.tasks.size();

                if (numTasks == 0) {
                    printMessage("No tasks saved");
                } else {
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
                }

                break;
            }
            case "done": {
                if (!hasArguments) {
                    throw new Exception("Command `done` requires an argument");
                }

                Task task;
                try {
                    int taskIndex = Integer.parseInt(arguments);
                    task = this.tasks.get(taskIndex - 1);
                    task.markCompleted();
                } catch (NumberFormatException e) {
                    throw new Exception("Unable to parse number from arguments: " + arguments);
                } catch (IndexOutOfBoundsException e) {
                    throw new Exception("There is no task with the following number: " + arguments);
                }

                printMessage("Marking task as completed:\n    " + task.toString());
                break;
            }
            case "todo": {
                Task todo = Todo.fromInput(arguments);
                this.tasks.add(todo);

                this.printTaskAddedMessage(todo);
                break;
            }
            case "deadline": {
                Task deadline = Deadline.fromInput(arguments);
                this.tasks.add(deadline);

                this.printTaskAddedMessage(deadline);
                break;
            }
            case "event": {
                Task event = Event.fromInput(arguments);
                this.tasks.add(event);

                this.printTaskAddedMessage(event);
                break;
            }
            default: {
                String fullInput = command + " " + arguments;
                throw new Exception("Command not recognized: " + fullInput);
            }
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
