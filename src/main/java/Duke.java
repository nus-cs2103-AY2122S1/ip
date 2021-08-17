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
    private static String DONE_COMMAND = "Nice! I've marked this task as done:";
    private static String DELETE_COMMAND = "Noted. I've removed this task:";

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
        String intro = "Hello! I'm Duke";
        String greeting = "What can I do for you?";

        divider();
        System.out.println(
                String.format("%4s%s\n%4s%s", " ", intro, " ", greeting)
        );
        divider();
    }

    /**
     * Method that prints Duke's exit message.
     */
    private static void exit() {
        String exitMessage ="Bye. Hope to see you again soon!";

        divider();
        System.out.println(String.format("%4s%s", " ", exitMessage));
        divider();
    }

    /**
     * Method that prints the current tasks in the task list.
     */
    private static void returnTaskList() {
        divider();
        System.out.println(String.format("%4sHere are the tasks in your list:", " "));
        System.out.println(taskList);
        divider();
    }

    /**
     * Method for Duke to handle invalid inputs by the user.
     *
     * @param input
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
                System.out.println(
                        String.format(
                                "%4s☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                                " ")
                );
        }
        return;
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
                System.out.println("Uncategorised task.");
                return;

        }

        divider();
        Duke.taskList = taskList.add(newTask);
        System.out.println(
                String.format("%4sGot it. I've added this task:\n%5s%s",
                        " ", " ", newTask)
        );
        System.out.println(
                String.format("%4s%s", " ", taskList.status())
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
            System.out.println(
                    String.format("%4s%s\n%6s%s\n%4s%s", " ",
                            DONE_COMMAND, " ", task, " ", taskList.status())
            );
        } else {
            System.out.println(String.format("%4s%s", " ", "There is no such task."));
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
            System.out.println(
                    String.format("%4s%s\n%6s%s\n%4s%s",
                            " ", DELETE_COMMAND, " ", task, " ", taskList.status())
            );
        } else {
            System.out.println(String.format("%4s%s", " ", "There is no such task."));
        }
        divider();
    }

    /**
     * Main method to execute Duke's functions.
     *
     * @param args
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
                    int taskIndex = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    if (command.startsWith("done")) {
                        Duke.markTaskAsCompleted(taskIndex);
                    } else {
                        Duke.deleteTask(taskIndex);
                    }
                } catch (NumberFormatException e){
                    System.out.println(e);
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
