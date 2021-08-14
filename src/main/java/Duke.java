import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String SEPARATOR = "_".repeat(50);
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        printOut(WELCOME_MESSAGE);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            switch(input) {
                case "list":
                    int index = 1;
                    String message = "";
                    for (Task task : tasks) {
                        message += String.format("%d. %s\n", index, task);
                        index++;
                    }
                    printOut(message.substring(0,message.length()-1));
                    break;
                default:
                    String[] commands = input.split("\\s+");
                    if (commands.length == 2 && commands[0].equals("done")) {
                        try {
                            Task task = tasks.get(Integer.parseInt(commands[1])-1);
                            if (task.markDone()) {
                                String msg = String.format("Nice! I've marked this task as done:\n    %s", task);
                                printOut(msg);
                            } else {
                                String msg = String.format("This task has already been marked as done:\n    %s", task);
                                printOut(msg);
                            }
                        } catch (NumberFormatException e) {
                            tasks.add(new Task(input));
                            printOut(String.format("added: %s", input));
                        }
                    } else {
                        tasks.add(new Task(input));
                        printOut(String.format("added: %s", input));
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        printOut(BYE_MESSAGE);
    }

    private static void printOut(String message) {
        System.out.println(String.format("%s\n%s\n%s", SEPARATOR, message, SEPARATOR));
    }
}
