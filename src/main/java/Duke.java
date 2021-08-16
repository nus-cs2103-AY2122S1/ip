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

    private static String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private static void quit() {
        reply("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        while(true) {
            String userInput = getUserInput();
            if (userInput.equals("list")) {
                reply(TaskManager.listTasks());
            } else if (userInput.equals("bye")) {
                quit();
                break;
            } else if (userInput.startsWith("done")){
                int id = Integer.parseInt(userInput.split(" ")[1]);
                // need to -1 due to 0-indexing
                reply(TaskManager.completeTask(id-1));
            } else {
                Task newTask = new Task(userInput);
                TaskManager.addTask(newTask);
                reply(String.format("added: %s", newTask.toString()));
            }
        }
    }
}
