import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Class encapsulating a Duke and its commands.
 */
public class Duke {
    /**
     * Field for duke to keep track of task list.
     */
    private static TaskList taskList = new TaskList();

    /**
     * Commands that Duke might use.
     */

    private enum Commands {
        GREET(String.format("Hello! I'm Duke\n%4sWhat can I do for you?", " ")),
        ADD("Got it. I've added this task:"),
        DONE("Nice! I've marked this task as done:"),
        DELETE("Noted. I've removed this task:"),
        LIST("Here are the tasks in your list:"),
        EXIT("Bye. Hope to see you again soon!"),
        INVALID("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
        NOSUCHTASK("There is no such task.");

        private final String command;

        private Commands(String command) {
            this.command = command;
        }

        public void printCommand() {
            System.out.println(String.format("%4s%s", " ", this.command));
        }
    }

    /**
     * Dividing line for formatting Duke's replies.
     */
    private static void divider() {
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));

        String line = String.format("%4s+%s+\n", " ", builder.toString());
        System.out.println(line);
    }

    /**
     * Method that prints Duke's greetings.
     */
    private static void greet() {
        divider();
        Commands.GREET.printCommand();
        divider();
    }

    /**
     * Method that prints Duke's exit message.
     */
    private static void exit() {
        divider();
        Commands.EXIT.printCommand();
        divider();
    }

    /**
     * Method that prints the current tasks in the task list.
     */
    private static void returnTaskList() {
        divider();
        Commands.LIST.printCommand();
        System.out.println(taskList);
        divider();
    }

    /**
     * Method for Duke to handle invalid inputs by the user.
     *
     * @param input The user input to Duke.
     */
    private static void handleInvalidInputs(String input) {
        switch (input) {
            case "todo":
            case "deadline":
            case "event": {
                System.out.println(
                        String.format(
                                "%4s☹ OOPS!!! The description of a %s cannot be empty.",
                                " ", input)
                );
                break;
            }
            default:
                Commands.INVALID.printCommand();
        }
    }

    /**
     * Method for Duke to update the task list according to the given tasks.
     *
     * @param command The command that specifies the type of task and its description.
     */
    private static void updateTaskList(String command) {
        // The type of the task indicated before the first space.
        int indexOfFirstSpace = command.indexOf(" ");

        // Only got one word or no description entered.
        if (indexOfFirstSpace == -1) {
            handleInvalidInputs(command);
            return;
        }
        String taskType = command.substring(0, indexOfFirstSpace);
        String description = command.substring(indexOfFirstSpace + 1);

        Task newTask;

        switch (taskType) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                int deadlineIndex = description.indexOf("/by");
                newTask = new Deadline(description.substring(0, deadlineIndex),
                        description.substring(deadlineIndex + 4));
                break;
            case "event":
                int timeIndex = description.indexOf("/at");
                newTask = new Event(description.substring(0, timeIndex),
                        description.substring(timeIndex + 4));
                break;
            default:
                Commands.INVALID.printCommand();
                return;

        }

        divider();
        Duke.taskList = taskList.add(newTask);
        Commands.ADD.printCommand();
        System.out.println(
                String.format("%5s%s\n%4s%s", " ", newTask,
                        " ", taskList.status())
        );
        divider();
    }

    /**
     * Method for Duke to mark the respective tasks as completed.
     *
     * @param index Index of the task to be deleted.
     */
    private static void markTaskAsCompleted(int index) {
        boolean isValid = taskList.isValidTaskIndex(index);
        divider();
        if (isValid) {
            Task task = taskList.markTaskAsCompleted(index);
            Commands.DONE.printCommand();
            System.out.println(
                    String.format("%6s%s\n%4s%s", " ", task,
                            " ", taskList.status())
            );
        } else {
            Commands.NOSUCHTASK.printCommand();
        }
        divider();
    }

    /**
     * Method for Duke to delete the corresponding task.
     *
     * @param index Index of the task to be deleted.
     */
    private static void deleteTask(int index) {
        boolean isValid = taskList.isValidTaskIndex(index);
        divider();
        if (isValid) {
            Task task = taskList.getTask(index);
            Duke.taskList = taskList.deleteTask(index);
            Commands.DELETE.printCommand();
            System.out.println(
                    String.format("%6s%s\n%4s%s", " ", task,
                            " ", taskList.status())
            );
        } else {
            Commands.NOSUCHTASK.printCommand();
        }
        divider();
    }

    /**
     * Main method to execute Duke's functions.
     *
     * @param args Command line arguments.
     */
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
            } else if (command.startsWith("done")
                    || command.startsWith("delete")) {
                try {
                    String[] arrOfCommandWords = command.split(" ", 2);
                    if (arrOfCommandWords.length <= 1) {
                        // No task specified.
                        Commands.INVALID.printCommand();
                        continue;
                    }
                    int taskIndex = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    if (command.startsWith("done")) {
                        Duke.markTaskAsCompleted(taskIndex);
                    } else {
                        Duke.deleteTask(taskIndex);
                    }
                } catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                } finally {
                    // TODO: implement cleanup
                }
            } else {
                // Add the task to the task list
                Duke.updateTaskList(command);
            }
        }
        sc.close();
    }
}
