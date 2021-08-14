import java.util.Scanner;

public class Duke {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";

    public static void main(String[] args) {
        greet();

        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String inputTask = scanner.nextLine();
        while (!inputTask.equals("bye")) {
            if (!inputTask.equals("list")) {
                if (taskList.addTask(inputTask)) {
                    displayText(space + "added: " + inputTask);
                } else {
                    System.exit(1);
                }
            } else {
                displayText(taskList.printList());
            }
            inputTask = scanner.nextLine();
        }

        displayText(space + "Bye. Hope to see you again soon!");
    }

    public static void greet() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(space + "Hello! I'm Duke\n" + space + "What can I do for you?");
        System.out.println(divider + "\n");
    }

    public static void displayText(String text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider + "\n");
    }
}
