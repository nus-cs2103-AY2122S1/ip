import java.util.Scanner;
import java.util.ArrayList;

public class Nyx {
    static final String INDENTATION = "    ";
    static final String LINE = INDENTATION + "________________________________________________________________________";
    public static void main(String[] args) {
        String logo = "     __      _\n"
                + "    |   \\   | |__    __ __     __\n"
                + "    | |\\ \\  | |\\ \\  / / \\ \\   / /\n"
                + "    | | \\ \\ | | \\ \\/ /   \\ \\ / / \n"
                + "    | |  \\ \\| |  \\  /    /    /\n"
                + "    | |   \\   |  / /    / / \\ \\\n"
                + "    |_|    \\__| /_/    /_/   \\_\\\n";
        System.out.println(LINE);
        System.out.println(INDENTATION + "Hello! This is\n" + logo);
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(LINE + "\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String command = sc.nextLine();
            System.out.println(LINE);

            if (command.equals("list")) {
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.printf("%s%d. %s%n",INDENTATION, i, taskList.get(i - 1));
                }
            } else if (command.equals("bye")) {
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!\n");
                System.out.println(LINE + "\n");
                break;
            } else {
                Task task = new Task(command);
                taskList.add(task);
                System.out.println(INDENTATION + "added: " + task);
            }
            System.out.println(LINE + "\n");
        }
    }
}