import java.util.Scanner;
import java.util.ArrayList;

public class Nyx {
    static final String LINE = "\t______________________________________________________\n";

    public static void main(String[] args) {
        String logo = "\t __      _\n"
                + "\t|   \\   | |__    __ __     __\n"
                + "\t| |\\ \\  | |\\ \\  / / \\ \\   / /\n"
                + "\t| | \\ \\ | | \\ \\/ /   \\ \\ / / \n"
                + "\t| |  \\ \\| |  \\  /    /    /\n"
                + "\t| |   \\   |  / /    / / \\ \\\n"
                + "\t|_|    \\__| /_/    /_/   \\_\\\n";
        System.out.println(LINE);
        System.out.println("\tHello! This is\n" + logo + "\n\tWhat can I do for you?");
        System.out.println(LINE);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String info = "";
            if (splitInput.length > 1) {
                info = splitInput[1].strip();
            }
            System.out.println(LINE);

            try {
                if (input.equals("list")) {
                    if (taskList.isEmpty()) {
                        System.out.println("\tYou do not have any task currently");
                    } else {
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 1; i <= taskList.size(); i++) {
                            System.out.printf("\t%d. %s%n", i, taskList.get(i - 1));
                        }
                    }
                } else if (input.equals("bye")) {
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                } else if (command.equals("done")) {
                    int index = Integer.parseInt(info) - 1;
                    taskList.get(index).setDone();
                    System.out.printf("\tNice! I've marked this task as done:%n\t  %s%n",
                            taskList.get(index));
                } else if (command.equals("todo")) {
                    if (info.isEmpty()) {
                        throw new NyxException("The description of a todo cannot be empty.");
                    }
                    ToDo task = new ToDo(info);
                    taskList.add(task);
                    System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
                    System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
                } else if (command.equals("deadline")) {
                    if (info.isEmpty()) {
                        throw new NyxException("The description of a deadline cannot be empty.");
                    }
                    String[] splitInfo = info.split("/");
                    String date = splitInfo[1].split(" ",2)[1];
                    Deadline task = new Deadline(splitInfo[0].strip(), date);
                    taskList.add(task);
                    System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
                    System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
                } else if (command.equals("event")) {
                    if (info.isEmpty()) {
                        throw new NyxException("The description of an event cannot be empty.");
                    }
                    String[] splitInfo = info.split("/");
                    String date = splitInfo[1].split(" ",2)[1];
                    Event task = new Event(splitInfo[0].strip(), date);
                    taskList.add(task);
                    System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
                    System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
                } else {
                    throw new NyxException("I dont understand this command... Please try again.");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("\tInvalid task index. Please try again!");
            } catch(NyxException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }
        sc.close();
    }
}