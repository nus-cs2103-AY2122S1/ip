import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    private final static int MAX_STORAGE = 100;

    private final static List<Task> TODO_LIST = new ArrayList<>();

    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        printLogo();
        greet();
        run();
    }

    public static void printLogo() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SPLIT);
    }

    public static void greet() {
        System.out.println('\t' + "Hello! I'm Duke");
        System.out.println('\t' + "What can I do for you?");
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void run() {
        String userInput = SCANNER.nextLine();
        userInput = userInput.trim();

        while (!userInput.equals("bye")) {
            checkUserInput(userInput);
            userInput = SCANNER.nextLine();
            userInput = userInput.trim();
        }
        exit();
    }

    public static void exit() {
        TODO_LIST.clear();
        SCANNER.close();
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String command) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + command);
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void checkUserInput(String userInput) {
        if (userInput.equals("list")) {
            displayList();

        } else if (userInput.startsWith("done")) {
            String taskNumberString = userInput.substring(4).trim();
            markTaskAsDone(taskNumberString);

        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(4).trim();
            addToList(new Todo(description));

        } else if (userInput.startsWith("event")) {
            String[] inputParts = userInput.substring(6).split("/at");
            String description = inputParts[0].trim();
            String timing = inputParts[1].trim();
            addToList(new Event(description, timing));

        } else if (userInput.startsWith("deadline")) {
            String[] inputParts = userInput.substring(8).split("/by");
            String description = inputParts[0].trim();
            String by = inputParts[1].trim();
            addToList(new Deadline(description, by));

        } else {
            echo("Invalid input: Please ensure instruction follows specified format");
        }
    }

    public static void addToList(Task newTask) {
        if (TODO_LIST.size() == MAX_STORAGE) {
            echo("Unable to add task: Task List is full");
            return;
        }
        TODO_LIST.add(newTask);
        echo("Got it. I've added this task:\n\t  " + newTask + "\n\tNow you have " +
                TODO_LIST.size() + " tasks in the list");
    }

    public static void markTaskAsDone(String taskId) {
        if (TODO_LIST.isEmpty()) {
            echo("task invalid: list is empty");
            return;
        }

        int taskNumber = Integer.parseInt(taskId);
        if (taskNumber < 0 || TODO_LIST.size() < taskNumber) {
            echo("task invalid: task does not exist");
            return;
        }
        Task selectedTask = TODO_LIST.get(taskNumber - 1); // shift to 0-indexing
        selectedTask.markAsDone();
        echo("Nice! I've marked this task as done:\n\t  " + selectedTask);
    }

    public static void displayList() {
        if (TODO_LIST.isEmpty()) {
            echo("list is empty: start adding tasks");
            return;
        }

        StringBuilder listStringBuilder = new StringBuilder();
        String newline = "\n\t";
        for (int idx = 0; idx < TODO_LIST.size(); idx ++) {
            Task task = TODO_LIST.get(idx);
            listStringBuilder.append(idx + 1); // shift to 1-indexing
            listStringBuilder.append(". ");
            listStringBuilder.append(task.toString());
            listStringBuilder.append(newline);
        }

        // clear final newline characters
        listStringBuilder.setLength(listStringBuilder.length() - newline.length());
        echo(listStringBuilder.toString());
    }
}
