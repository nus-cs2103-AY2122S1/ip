import java.util.Scanner;
import java.util.List;
import java.lang.StringBuilder;

public class Duke {
    private static String WELCOME_TEXT = "Hey there I'm Duke!\n" + "How can I help you today?";
    private static String BYE_TEXT = "Bye! Hope to see you again!";

    private Scanner in;
    private List<Task> tasks;
    private Storage storage;
    private boolean shouldExit;

    public enum Command {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT,
    }

    Duke(Scanner in, String filePath) throws Exception {
        this.in = in;
        this.shouldExit = false;
        this.storage = new Storage(filePath);
        this.tasks = storage.loadTasks();
    }

    private void greet() {
        printMessage(WELCOME_TEXT);
    }

    private void handleInput() throws Exception {
        System.out.print("> ");
        String commandString = this.in.next();
        String arguments = this.in.nextLine().trim();
        boolean hasArguments = arguments.length() != 0;

        Command command;
        try {
            command = Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            String fullInput = commandString + " " + arguments;
            throw new Exception("Command not recognized: " + fullInput);
        }

        switch (command) {
            case BYE: {
                if (hasArguments) {
                    throw new Exception("Command `bye` does not accept arguments");
                }

                this.shouldExit = true;
                printMessage(BYE_TEXT);
                break;
            }
            case LIST: {
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
            case DONE: {
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

                this.storage.saveTasks(this.tasks);
                printMessage("Marking task as completed:\n    " + task.toString());
                break;
            }
            case DELETE: {
                if (!hasArguments) {
                    throw new Exception("Command `delete` requires an argument");
                }

                Task task;
                try {
                    int taskIndex = Integer.parseInt(arguments);
                    task = this.tasks.remove(taskIndex - 1);
                } catch (NumberFormatException e) {
                    throw new Exception("Unable to parse number from arguments: " + arguments);
                } catch (IndexOutOfBoundsException e) {
                    throw new Exception("There is no task with the following number: " + arguments);
                }

                this.storage.saveTasks(this.tasks);
                printMessage("Removed the following task:\n    " + task.toString() + "\n" + "You now have "
                        + this.tasks.size() + " tasks in your list.");
                break;
            }
            case TODO: {
                Task todo = Todo.fromInput(arguments);
                this.tasks.add(todo);

                this.storage.saveTasks(this.tasks);
                this.printTaskAddedMessage(todo);
                break;
            }
            case DEADLINE: {
                Task deadline = Deadline.fromInput(arguments);
                this.tasks.add(deadline);

                this.storage.saveTasks(this.tasks);
                this.printTaskAddedMessage(deadline);
                break;
            }
            case EVENT: {
                Task event = Event.fromInput(arguments);
                this.tasks.add(event);

                this.storage.saveTasks(this.tasks);
                this.printTaskAddedMessage(event);
                break;
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

    private static void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(input, filePath);
        } catch (Exception e) {
            printMessage("Unable to initialize data file");
            input.close();
            return;
        }

        duke.greet();
        while (!duke.shouldExit()) {
            try {
                duke.handleInput();
            } catch (Exception e) {
                // TODO: custom Duke exceptions?
                printMessage("Error: " + e.getMessage());
            }
        }

        input.close();
    }
}
