import java.util.Scanner;
import java.lang.StringBuilder;

public class Duke {
    private Scanner in;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private boolean shouldExit;

    public enum Command {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT,
    }

    Duke(Scanner in, String filePath) throws Exception {
        this.in = in;
        this.shouldExit = false;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
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
                break;
            }
            case LIST: {
                if (hasArguments) {
                    throw new Exception("Command `list` does not accept arguments");
                }

                StringBuilder builder = new StringBuilder();
                int numTasks = this.taskList.size();

                if (numTasks == 0) {
                    ui.printMessage("No tasks saved");
                } else {
                    for (int i = 0; i < numTasks; i++) {
                        Task item = this.taskList.getTask(i);
                        builder.append(i + 1);
                        builder.append(". ");
                        builder.append(item.toString());
                        if (i < numTasks - 1) {
                            builder.append("\n");
                        }
                    }

                    ui.printMessage(builder.toString());
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
                    task = this.taskList.getTask(taskIndex - 1);
                    task.markCompleted();
                } catch (NumberFormatException e) {
                    throw new Exception("Unable to parse number from arguments: " + arguments);
                } catch (IndexOutOfBoundsException e) {
                    throw new Exception("There is no task with the following number: " + arguments);
                }

                this.storage.saveTasks(this.taskList);
                ui.printMessage("Marking task as completed:\n    " + task.toString());
                break;
            }
            case DELETE: {
                if (!hasArguments) {
                    throw new Exception("Command `delete` requires an argument");
                }

                Task task;
                try {
                    int taskIndex = Integer.parseInt(arguments);
                    task = this.taskList.removeTask(taskIndex - 1);
                } catch (NumberFormatException e) {
                    throw new Exception("Unable to parse number from arguments: " + arguments);
                } catch (IndexOutOfBoundsException e) {
                    throw new Exception("There is no task with the following number: " + arguments);
                }

                this.storage.saveTasks(this.taskList);
                ui.printMessage("Removed the following task:\n    " + task.toString() + "\n" + "You now have "
                        + this.taskList.size() + " tasks in your list.");
                break;
            }
            case TODO: {
                Task todo = Todo.fromInput(arguments);
                this.taskList.addTask(todo);

                this.storage.saveTasks(this.taskList);
                this.printTaskAddedMessage(todo);
                break;
            }
            case DEADLINE: {
                Task deadline = Deadline.fromInput(arguments);
                this.taskList.addTask(deadline);

                this.storage.saveTasks(this.taskList);
                this.printTaskAddedMessage(deadline);
                break;
            }
            case EVENT: {
                Task event = Event.fromInput(arguments);
                this.taskList.addTask(event);

                this.storage.saveTasks(this.taskList);
                this.printTaskAddedMessage(event);
                break;
            }
        }
    }

    private void printTaskAddedMessage(Task task) {
        ui.printMessage("Added the following task:\n    " + task.toString() + "\n" + "You now have " + this.taskList.size()
                + " tasks in your list.");
    }

    private boolean shouldExit() {
        return this.shouldExit;
    }

    public void run() {
        ui.printGreeting();

        while (!shouldExit()) {
            try {
                handleInput();
            } catch (Exception e) {
                // TODO: custom Duke exceptions?
                ui.printMessage("Error: " + e.getMessage());
            }
        }

        ui.printGoodbye();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(input, filePath);
        } catch (Exception e) {
            // TODO: figure out static/non-static Ui class
            System.out.println("Unable to initialize data file");
            input.close();
            return;
        }

        duke.run();
        input.close();
    }
}
