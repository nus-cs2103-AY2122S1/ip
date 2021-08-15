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
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String info = "";
            if (splitInput.length > 1) {
                info = splitInput[1];
            }
            System.out.println(LINE);

            if (input.equals("list")) {
                System.out.println(INDENTATION + "Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.printf("%s%d. %s%n",INDENTATION, i, taskList.get(i - 1));
                }
            } else if (input.equals("bye")) {
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!\n");
                System.out.println(LINE + "\n");
                break;
            } else if (command.equals("done")) {
                if (info.matches("\\d") && (Integer.parseInt(info) - 1) < taskList.size()) {
                    int index = Integer.parseInt(info) - 1;
                    taskList.get(index).setDone();
                    System.out.printf("%sNice! I've marked this task as done:%n%s  %s%n",
                            INDENTATION, INDENTATION, taskList.get(index));
                } else {
                    System.out.println(INDENTATION + "Invalid task index. Please try again!");
                }
            } else if (command.equals("todo")) {
                ToDo task = new ToDo(info);
                taskList.add(task);
                System.out.printf("%sGot it. I've added this task:%n%s  %s%n",
                        INDENTATION, INDENTATION, task);
                System.out.printf("%sNow you have %d tasks in the list.%n", INDENTATION, taskList.size());
            } else if (command.equals("deadline")) {
                String[] splitInfo = info.split("/");
                String date = splitInfo[1].split(" ",2)[1];
                Deadline task = new Deadline(splitInfo[0].strip(), date);
                taskList.add(task);
                System.out.printf("%sGot it. I've added this task:%n%s  %s%n",
                        INDENTATION, INDENTATION, task);
                System.out.printf("%sNow you have %d tasks in the list.%n", INDENTATION, taskList.size());
            } else if (command.equals("event")) {
                String[] splitInfo = info.split("/");
                String date = splitInfo[1].split(" ",2)[1];
                Event task = new Event(splitInfo[0].strip(), date);
                taskList.add(task);
                System.out.printf("%sGot it. I've added this task:%n%s  %s%n",
                        INDENTATION, INDENTATION, task);
                System.out.printf("%sNow you have %d tasks in the list.%n", INDENTATION, taskList.size());
            } else {
                System.out.println("I dont understand this command... Please try again.");
            }
            System.out.println(LINE + "\n");
        }
    }
}