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
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    // need to -1 due to 0-indexing
                    reply(TaskManager.completeTask(id - 1));
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
