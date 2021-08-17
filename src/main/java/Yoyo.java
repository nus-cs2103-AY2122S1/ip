import java.io.IOException;
import java.util.Scanner;

public class Yoyo {
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    private static class YoyoIncompleteCommandException extends IOException {
        YoyoIncompleteCommandException(String message) {
            super(message);
        }
    }

    private static class YoyoCommandNotFoundException extends IOException {
        YoyoCommandNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Prints a decoration line for output.
     */
    private static void outputWrapper() {
        System.out.println("============================================================");
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Yoyo.\n"
                + "What can I do for you?";
        outputWrapper();
        System.out.println(greetings);
        outputWrapper();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] inputWords = input.split(" ", 2);
            String command = inputWords[0];
            try {
                if (command.equals("bye")) {
                    outputWrapper();
                    System.out.println("Bye. Hope to see you again soon!");
                    outputWrapper();
                    break;
                } else if (command.equals("list")) {
                    outputWrapper();
                    if (numTasks == 0) {
                        System.out.println("You have no task at the moment.");
                    } else {
                        for (int i = 0; i < numTasks; i++) {
                            System.out.println(i + 1 + "." + tasks[i].showStatus());
                        }
                    }
                    outputWrapper();
                } else if (command.equals("done")) {
                    try {
                        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                        tasks[taskIndex].toggleDone();
                        outputWrapper();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + tasks[taskIndex].showStatus());
                        outputWrapper();
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                        outputWrapper();
                        System.out.println("Please enter a valid index!");
                        outputWrapper();
                    }
                } else {

                    if (command.equals("todo")) {
                        checkCompleteCommand(inputWords);
                        Task newTask = new Todo(inputWords[1]);
                        tasks[numTasks] = newTask;
                        numTasks++;
                        printAddMessage(newTask);
                    } else if (command.equals("event")) {
                        checkCompleteCommand(inputWords);
                        String[] taskInfo = inputWords[1].split(" /at ");
                        if (taskInfo.length < 2 ) {
                            throw new YoyoIncompleteCommandException("You have not entered enough information for"
                                    + " your command.");
                        } else {
                            Task newTask = new Event(taskInfo[0], taskInfo[1]);
                            tasks[numTasks] = newTask;
                            numTasks++;
                            printAddMessage(newTask);
                        }
                    } else if (command.equals("deadline")) {
                        checkCompleteCommand(inputWords);
                        String[] taskInfo = inputWords[1].split(" /by ");
                        if (taskInfo.length < 2) {
                            throw new YoyoIncompleteCommandException("You have not entered enough information for"
                                    + " your command.");
                        } else {
                            Task newTask = new Deadline(taskInfo[0], taskInfo[1]);
                            tasks[numTasks] = newTask;
                            numTasks++;
                            printAddMessage(newTask);
                        }
                    } else {
                        throw new YoyoCommandNotFoundException("Yoyo doesn't understand what you mean :-(");
                    }
                }
            } catch (YoyoCommandNotFoundException | YoyoIncompleteCommandException e) {
                outputWrapper();
                System.out.println(e.getMessage());
                outputWrapper();
            }
        }
    }

    /**
     * prints success message for adding task.
     *
     * @param newTask The task that has been created.
     */
    private static void printAddMessage(Task newTask) {
        outputWrapper();
        System.out.print("Got it. I've added this task:\n   "
                + newTask.showStatus()
                + "\nNow you have "
                + numTasks
                + " tasks in the list.\n");
        outputWrapper();
    }

    /**
     * Checks user input for incomplete commands.
     *
     * @param strArr String array from user input.
     * @throws YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    private static void checkCompleteCommand(String[] strArr) throws YoyoIncompleteCommandException {
        if (strArr.length < 2 || strArr[1].trim().length() == 0) {
            throw new YoyoIncompleteCommandException("You have not entered enough information for your command.");
        }
    }
}
