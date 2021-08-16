import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        try {
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                String input = scanner.nextLine();
                printLine();
                if (input.equals("bye")) {
                    System.out.println("    " + "Bye. Hope to see you again soon!");
                    run = false;
                } else if (input.equals("list")) {
                    listItems();
                } else if (input.contains("done ")) {
                    if (input.length() < 6) {
                        throw new Exception("Done command not followed with a number");
                    }
                    int number = Integer.parseInt(input.substring(5));
                    System.out.println("    Nice! I've marked this task as done: ");
                    list.get(number-1).markedAsDone();
                    System.out.println("      [X] "+list.get(number-1).getDescription());
                } else {
                    addItem(input);
                }
                printLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void listItems() {
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            System.out.println("    " + num + ".[" + list.get(i).getStatusIcon()+ "] "+list.get(i).getDescription());
        }
    }

    public static void addItem(String s) {
        Task t = new Task(s);
        list.add(t);
        System.out.println("    added: " + s);
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
