import java.util.Scanner;

public class Duke {
    private final static String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final static Scanner SCANNER = new Scanner(System.in);
    private static String[] tasks = new String[100];
    private static int numTasks = 0;

    private static void greet() {
        System.out.println(GREETING_MSG);
    }

    private static void exit() {
        System.out.println(GOODBYE_MSG);
    }

    private static void addTask(String task) {
        tasks[numTasks++] = task;
        System.out.println(String.format("added: %s", task));
    }

    private static void listTasks() {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i]));
        }
    }


    public static void main(String[] args) {
        greet();
        for (String s = SCANNER.nextLine(); !s.equals("bye"); s = SCANNER.nextLine()) {
            if (s.equals("list")) {
                listTasks();
            } else {
                addTask(s);
            }
        }
        exit();
    }
}
