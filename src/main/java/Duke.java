import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static void reply(String msg) {
        String indentedMsg = Arrays.asList(msg.split("\n")).stream()
                                   .map(s -> String.format("\t%s\n", s))
                                   .collect(Collectors.joining(""));
        System.out.printf(
                        "\t____________________________________\n" +
                        "%s" +
                        "\t____________________________________\n", indentedMsg);
    }

    private static void greet() {
        reply("Hello! I'am Duke\n" +
                "What can I do for you?");
    }

    private static void quit() {
        reply("Bye. Hope to see you again soon!");
    }

    private static void addTask(String input) throws DukeException, IllegalArgumentException {
        Task addedTask = TaskManager.addTask(input);
        reply(String.format("Got it. I've added this task: \n" +
                "%s\n" +
                "Now you have %d tasks in the list", addedTask.toString(), TaskManager.taskCount()));
    }

    private static void completeTask(String input) {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length < 2) {
            throw new IllegalArgumentException("Not enough argument for done");
        }
        int id = Integer.parseInt(parsedInput[1]);
        reply(String.format(
                "Nice! I've marked this task as done: \n" +
                "%s", TaskManager.completeTask(id - 1).toString()
        ));
    }

    private static void deleteTask(String input) throws IllegalArgumentException {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length < 2) {
            throw new IllegalArgumentException("Not enough argument for delete");
        }
        int id = Integer.parseInt(parsedInput[1]);
        Task deletedTask = TaskManager.deleteTask(id);
        reply(String.format("Noted. I've removed this task: \n" +
                "%s\n" +
                "Now you have %d tasks in the list.", deletedTask.toString(), TaskManager.taskCount()));
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userInput = scanner.nextLine();
            try {
                if (userInput.equals("list")) {
                    reply(TaskManager.listTasks());
                } else if (userInput.equals("bye")) {
                    quit();
                    break;
                } else if (userInput.startsWith("done")) {
                    completeTask(userInput);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    addTask(userInput);
                }
            } catch (IllegalArgumentException e) {
                reply(e.getMessage());
            } catch (DukeException e) {
                reply(e.getMessage());
            } catch (Exception e) {
                // catch unhandled exceptions
                e.printStackTrace();
            }
        }
    }
}
