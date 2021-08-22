import java.util.Scanner;
import java.util.ArrayList;

public class Nyx {
    private static final String LINE = "\t____________________________________________________________\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static void respond(String input) throws NyxException,
            NumberFormatException, IndexOutOfBoundsException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String info = "";
        if (splitInput.length > 1) {
            info = splitInput[1].strip();
        }
        System.out.println(LINE);

        switch (command) {
        case "list":
            if (taskList.isEmpty()) {
                System.out.println("\tYou do not have any task currently");
            } else {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.printf("\t%d. %s%n", i, taskList.get(i - 1));
                }
            }
            break;
        case "done":
            int index = Integer.parseInt(info) - 1;
            if (!taskList.isEmpty()) {
                taskList.get(index).setDone();
                System.out.printf("\tNice! I've marked this task as done:%n\t  %s%n",
                        taskList.get(index));
            } else {
                System.out.println("\tNo task to mark!");
            }
            break;
        case "todo": {
            if (info.isEmpty()) {
                throw new NyxException("The description of a todo cannot be empty.");
            }
            ToDo task = new ToDo(info);
            taskList.add(task);
            System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
            System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
            break;
        }
        case "bye": {
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println(LINE);
            break;
        }
        case "deadline": {
            if (info.isEmpty()) {
                throw new NyxException("The description of a deadline cannot be empty.");
            }
            String[] splitInfo = info.split(" /by ");
            Deadline task = new Deadline(splitInfo[0].strip(), splitInfo[1]);
            taskList.add(task);
            System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
            System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
            break;
        }
        case "event": {
            if (info.isEmpty()) {
                throw new NyxException("The description of an event cannot be empty.");
            }
            String[] splitInfo = info.split(" /at ");
            Event task = new Event(splitInfo[0].strip(), splitInfo[1]);
            taskList.add(task);
            System.out.printf("\tGot it. I've added this task:%n\t  %s%n", task);
            System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
            break;
        }
        case "delete":
            index = Integer.parseInt(info) - 1;
            if (!taskList.isEmpty()) {
                Task task = taskList.get(index);
                taskList.remove(index);
                System.out.printf("\tNoted! I've removed this task:%n\t  %s%n", task);
                System.out.printf("\tNow you have %d tasks in the list.%n", taskList.size());
            } else {
                System.out.println("\tNo task to delete!");
            }
            break;
        default:
            throw new NyxException("I dont understand this command... Please try again.");
        }
    }

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
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                respond(input);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("\tInvalid task index. Please try again!");
            } catch(NyxException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
                input = sc.nextLine();
            }
        }
        respond("bye");
        sc.close();
    }
}