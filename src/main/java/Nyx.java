import java.util.Scanner;
import java.util.ArrayList;

public class Nyx {
    static final String INDENTATION = "    ";
    static final String LINE = INDENTATION + "______________________________________________________";

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
                System.out.println(INDENTATION + "Here are the tasks in your list:\n");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.printf("%s%d. %s%n",INDENTATION, i, taskList.get(i - 1));
                }
            } else if (command.equals("bye")) {
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!\n");
                System.out.println(LINE + "\n");
                break;
            } else if (command.contains("done ")) {
                String taskNum = command.split(" ")[1];

                if (taskNum.matches("\\d") && (Integer.parseInt(taskNum) - 1) < taskList.size()) {
                    int index = Integer.parseInt(taskNum) - 1;
                    taskList.get(index).setDone();
                    System.out.printf("%sNice! I've marked this task as done:%n%s  %s%n",
                            INDENTATION, INDENTATION, taskList.get(index));
                } else {
                    System.out.println(INDENTATION + "Invalid task index. Please try again!");
                }
            } else {
                Task task = new Task(command);
                taskList.add(task);
                System.out.println(INDENTATION + "added: " + command);
            }
            System.out.println(LINE + "\n");
        }
    }
}