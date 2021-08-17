import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Scanner scanner;

    public static void main(String[] args) {
        printLogo();
        greet();
        run();
        exit();
    }

    public static void printLogo() {
        Echoer.print("Hello from\n" + LOGO);
    }

    public static void greet() {
        Echoer.echo("Hello! I'm Duke.\n\tWhat can I do for you?");
    }

    public static void run() {
        scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        while (!userInput.equals("bye")) {
            checkUserInput(userInput);
            userInput = scanner.nextLine().trim();
        }
        scanner.close();
    }

    public static void exit() {
        Echoer.echo("Bye. Hope to see you again soon!");
    }

    public static void checkUserInput(String userInput) {
        if (userInput.equals("list")) {
            TaskManager.listTasks();

        } else if (userInput.startsWith("done")) {
            String taskNumberString = userInput.substring(4).trim();
            int taskNumber = Integer.parseInt(taskNumberString);
            TaskManager.markTaskAsDone(taskNumber);

        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(4).trim();
            TaskManager.addToList(new Todo(description));

        } else if (userInput.startsWith("event")) {
            String[] inputParts = userInput.substring(6).split("/at");
            String description = inputParts[0].trim();
            String timing = inputParts[1].trim();
            TaskManager.addToList(new Event(description, timing));

        } else if (userInput.startsWith("deadline")) {
            String[] inputParts = userInput.substring(8).split("/by");
            String description = inputParts[0].trim();
            String by = inputParts[1].trim();
            TaskManager.addToList(new Deadline(description, by));

        } else {
            Echoer.echo("Invalid input: Please ensure instruction follows specified format.");
        }
    }
}
