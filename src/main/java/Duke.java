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
                taskList.list();
            } else {
                taskList.add(msg);
            }
        }
    }

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
