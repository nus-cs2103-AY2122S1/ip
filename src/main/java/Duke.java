import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String SEPARATOR = "_".repeat(50);
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static void parseInput(String input, List<Task> tasks) {
        String[] commands = input.split("\\s+");
        String keyWord = commands[0];
        String taskDetails;
        String extraDetails;
        String message = "";
        Task newTask = null;
        int index = 0;
        switch(keyWord) {
            case "list":
                index = 1;
                for (Task task : tasks) {
                    message += String.format("%d. %s\n", index, task);
                    index++;
                }
                message = message.substring(0,message.length()-1);
                break;
            case "todo":
                taskDetails = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length));
                newTask = new Todo(taskDetails);
                break;
            case "deadline":
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/by")) {
                        index = i;
                        break;
                    }
                }
                taskDetails = String.join(" ", Arrays.copyOfRange(commands, 1, index));
                extraDetails = String.join(" ", Arrays.copyOfRange(commands, index+1, commands.length));
                newTask = new Deadline(taskDetails, extraDetails);
                break;
            case "event":
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/at")) {
                        index = i;
                        break;
                    }
                }
                taskDetails = String.join(" ", Arrays.copyOfRange(commands, 1, index));
                extraDetails = String.join(" ", Arrays.copyOfRange(commands, index+1, commands.length));
                newTask = new Deadline(taskDetails, extraDetails);
                break;
            case "done":
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        message = String.format("Sorry, this task index is out of bounds! Please input an integer between 0-%d", tasks.size());
                    } else {
                        Task task = tasks.get(taskIndex);
                        if (task.markDone()) {
                            message = String.format("Nice! I've marked this task as done:\n    %s", task);
                        } else {
                            message = String.format("This was completed previously!:\n    %s", task);
                        }
                    }
                } catch (NumberFormatException e) {
                    message = String.format("Sorry, this task index is invalid! Please input an integer between 0-%d", tasks.size());
                }
        }
        if (newTask != null) {
            tasks.add(newTask);
            message = String.format(
                    "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.",
                    newTask,
                    tasks.size()
            );
        }
        printOut(message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        printOut(WELCOME_MESSAGE);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            parseInput(input, tasks);
            input = scanner.nextLine();
        }
        printOut(BYE_MESSAGE);
    }

    private static void printOut(String message) {
        System.out.println(String.format("%s\n%s\n%s", SEPARATOR, message, SEPARATOR));
    }
}
