import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "_________________________________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        greet();

        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                exit();
            } else if (msg.equals("list")) {
                printMessage(taskList.toString());
            } else {
                String[] words = msg.split(" ");

                // switch statement for the first word
                switch (words[0]) {
                    case "done":
                        taskList.doTask(words[1]);
                        break;
                    case "todo":
                        taskList.addToDo(msg);
                        break;
                    case "deadline":
                        taskList.addDeadline(msg);
                        break;
                    case "event":
                        taskList.addEvent(msg);
                        break;
                    default:
                        Duke.printMessage("Unsupported operation.");
                        break;
                }
            }
        }
    }

    // prints a message between two horizontal line separators
    public static void printMessage(String msg) {
        System.out.println(LINE_SEPARATOR + msg + "\n" + LINE_SEPARATOR);
    }

    private static void greet() {
        String greeting = "Hello! I'm iP Man.\nWhat can I do for you?";
        printMessage(greeting);
    }

    private static void exit() {
        printMessage("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
