import java.util.Scanner;

public class Duke {
    protected static final String HORIZONTAL_LINE = "  -----------------------";
    protected static final String INDENTATION = "    ";

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello, I am Duke.");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        TaskManagement taskManagement = new TaskManagement();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    bye();
                    sc.close();
                    return;
                case "list":
                    taskManagement.showTasks();
                    break;
                default:
                    showMessage("added: " + taskManagement.addTask(input));
            }
        }
        sc.close();
        return;
    }
}
