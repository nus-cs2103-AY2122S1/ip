import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Yoyo {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Exception class for incomplete command .
     */
    private static class YoyoIncompleteCommandException extends IOException {
        YoyoIncompleteCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for invalid command.
     */
    private static class YoyoCommandNotFoundException extends IOException {
        YoyoCommandNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for empty command.
     */
    private static class YoyoEmptyCommandException extends IOException {
        YoyoEmptyCommandException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Yoyo.\n"
                + "What can I do for you?";
        outputWrapper();
        System.out.println(greetings);
        outputWrapper();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                input = input.trim();
                if (input.length() == 0) {
                    throw new YoyoEmptyCommandException("You have not entered a command!");
                }
                String[] inputTokens = input.split(" ", 2);
                String command = inputTokens[0];
                int currListLength = tasks.size();

                if (command.equals("bye")) {
                    outputWrapper();
                    System.out.println("Bye. Hope to see you again soon!");
                    outputWrapper();
                    break;
                } else if (command.equals("list")) {
                    outputWrapper();
                    if (currListLength == 0) {
                        System.out.println("You have no task at the moment.");
                    } else {
                        for (int i = 0; i < currListLength; i++) {
                            System.out.println(i + 1 + "." + tasks.get(i).showStatus());
                        }
                    }
                    outputWrapper();
                } else if (command.equals("done")) {
                    checkCompleteCommand(inputTokens);
                    try {
                        int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
                        tasks.get(taskIndex).toggleDone();
                        outputWrapper();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + tasks.get(taskIndex).showStatus());
                        outputWrapper();
                    } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                        outputWrapper();
                        System.out.println("Please enter a valid index!");
                        outputWrapper();
                    }
                } else if (command.equals("delete")) {
                    checkCompleteCommand(inputTokens);
                    try {
                        int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
                        Task toRemove = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        outputWrapper();
                        System.out.println("Noted. I've removed this task:\n"
                                + toRemove.showStatus()
                                + "\nNow you have "
                                + tasks.size()
                                + " tasks in the list.");
                        outputWrapper();
                    } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                        outputWrapper();
                        System.out.println("Please enter a valid index!");
                        outputWrapper();
                    }

                } else {

                    if (command.equals("todo")) {
                        checkCompleteCommand(inputTokens);
                        Task newTask = new Todo(inputTokens[1]);
                        tasks.add(newTask);
                        printAddMessage(newTask);
                    } else if (command.equals("event")) {
                        checkCompleteCommand(inputTokens);
                        String[] taskInfo = inputTokens[1].split(" /at ");
                        if (taskInfo.length < 2 ) {
                            throw new YoyoIncompleteCommandException("You have not entered enough information for"
                                    + " your command.");
                        } else {
                            Task newTask = new Event(taskInfo[0], taskInfo[1]);
                            tasks.add(newTask);
                            printAddMessage(newTask);
                        }
                    } else if (command.equals("deadline")) {
                        checkCompleteCommand(inputTokens);
                        String[] taskInfo = inputTokens[1].split(" /by ");
                        if (taskInfo.length < 2) {
                            throw new YoyoIncompleteCommandException("You have not entered enough information for"
                                    + " your command.");
                        } else {
                            Task newTask = new Deadline(taskInfo[0], taskInfo[1]);
                            tasks.add(newTask);
                            printAddMessage(newTask);
                        }
                    } else {
                        throw new YoyoCommandNotFoundException("Yoyo doesn't understand what you mean :-(");
                    }
                }
            } catch (YoyoCommandNotFoundException | YoyoIncompleteCommandException
                        | YoyoEmptyCommandException e) {
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
                + tasks.size()
                + " tasks in the list.\n");
        outputWrapper();
    }

    /**
     * Checks user input for incomplete commands.
     *
     * @param inputTokens String array from user input.
     * @throws YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    private static void checkCompleteCommand(String[] inputTokens) throws YoyoIncompleteCommandException {
        if (inputTokens.length < 2 || inputTokens[1].trim().length() == 0) {
            throw new YoyoIncompleteCommandException("You have not entered enough information for your command.");
        }
    }

    /**
     * Prints a decoration line for output.
     */
    private static void outputWrapper() {
        System.out.println("============================================================");
    }
}
