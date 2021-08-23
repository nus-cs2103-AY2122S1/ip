import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "_________________________________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        init(taskList);

        while (sc.hasNextLine()) {
            try {
                String msg = sc.nextLine();

                // require exact match for these commands
                if (Commands.EXIT.isCommand(msg)) {
                    exit();
                } else if (Commands.LIST.isCommand(msg)) {
                    printMessage(taskList.toString());
                } else {
                    String[] words = msg.split(" ");

                    // check if the first word matches any command
                    if (Commands.DONE.isCommand(words[0])) {
                        taskList.doTask(words[1]);
                    } else if (Commands.DELETE.isCommand(words[0])) {
                        taskList.deleteTask(words[1]);
                    } else if (Commands.TODO.isCommand(words[0])) {
                        taskList.addToDo(msg);
                    } else if (Commands.DEADLINE.isCommand(words[0])) {
                        taskList.addDeadline(msg);
                    } else if (Commands.EVENT.isCommand(words[0])) {
                        taskList.addEvent(msg);
                    } else {
                        throw new DukeException("Unsupported operation");
                    }
                }
            } catch (DukeException dukeException) {
                dukeException.displayError();
            }
        }
    }

    // prints a message between two horizontal line separators
    public static void printMessage(String msg) {
        System.out.println(LINE_SEPARATOR + msg + "\n" + LINE_SEPARATOR);
    }

    private static void init(TaskList taskList) {
        String greeting;

        if (taskList.loadFile()) {
            // file successfully loaded, it's a returning user. Print the retrieved list.
            greeting = "Welcome back! What can I do for you?\n\n" +
                    "Tasks in list:\n" + taskList.toString();
        } else {
            // file does not exist, it's a new user
            greeting = "Hello! I'm iP Man.\nWhat can I do for you?";
        }

        printMessage(greeting);
    }

    private static void exit() {
        printMessage("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
