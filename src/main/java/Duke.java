import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Duke {
    private static final HashMap<String, Function<Optional<String>, String>> commandTable = new HashMap<>();
    private static void initialize() {
        commandTable.put("list", x -> listTask());
        commandTable.put("done", x -> completeTask(parseId(x)));
        commandTable.put("delete", x -> deleteTask(parseId(x)));
        commandTable.put("todo", x -> addTask("todo", x));
        commandTable.put("deadline", x -> addTask("deadline", x));
        commandTable.put("event", x -> addTask("event", x));
    }

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
        System.exit(0);
    }

    private static int parseId(Optional<String> input) throws IllegalArgumentException{
        return Integer.parseInt(input.orElseThrow(() -> new IllegalArgumentException("Not enough arguments")));
    }

    private static String listTask() {
        return String.format("Here are the tasks in your list:\n%s", TaskManager.listTasks());
    }

    private static String addTask(String type, Optional<String> args) {
        try {
            Task addedTask = TaskManager.addTask(type, args);
            return String.format("Got it. I've added this task: \n" +
                    "%s\n" +
                    "Now you have %d tasks in the list", addedTask.toString(), TaskManager.taskCount());
        } catch (DukeException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private static String completeTask(int id) {
        return String.format(
                "Nice! I've marked this task as done: \n" +
                "%s", TaskManager.completeTask(id - 1).toString()
        );
    }

    private static String deleteTask(int id) {
        Task deletedTask = TaskManager.deleteTask(id);
        return String.format("Noted. I've removed this task: \n" +
                "%s\n" +
                "Now you have %d tasks in the list.", deletedTask.toString(), TaskManager.taskCount());
    }

    public static void main(String[] args) {
        greet();
        initialize();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];
            Optional<String> cmdArg = inputs.length > 1 ? Optional.of(inputs[1]) : Optional.empty();
            if (command.equals("bye")) {
                quit();
            }
            try {
                reply(
                    Optional.ofNullable(commandTable.get(command))
                            .orElseThrow(() -> new InvalidDukeCommandException())
                            .apply(cmdArg)
                );
            } catch (IllegalArgumentException | DukeException e) {
                reply(e.getMessage());
            }
        }
    }
}
