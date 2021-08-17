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
        System.out.println(String.format("%4s%s", " ", exitMessage));
        divider();
    }

    private static void returnTaskList() {
        divider();
        System.out.println(String.format("%4sHere are the tasks in your list:", " "));
        System.out.println(taskList);
        divider();
    }

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
        sc.close();
    }
}
