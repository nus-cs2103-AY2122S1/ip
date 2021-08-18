import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> list = new ArrayList<>();

    private static void addToList(Task task) {
        list.add(task);
    }

    private static void listTasks() {
        if (list.size() < 1) {
            System.out.println("You haven't added anything to the list yet! Here, add something.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    private static void completeTask(int index) {
        Task task = list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done: \n" + "  " + task);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) { //Close Duke
                break;
            } else if (input.equals("list")) { //List current tasks and prompt for more input
                listTasks();
            } else if (input.startsWith("done")) {
                try { //Treat the substring after 'done' as an int.
                    int listIndex = Integer.parseInt(input.substring(5));
                    if (listIndex <= list.size() && listIndex >= 1) {
                        completeTask(listIndex);
                    } else {
                        System.out.println("Couldn't find that task in the list! Try again.");
                    }
                } catch (NumberFormatException e) { //If 'done' is followed by a non-integer
                    System.out.println("Please make sure only a number follows the command 'done'. Try again.");
                } catch (StringIndexOutOfBoundsException e) { //If 'done' is not followed by anything
                    System.out.println("Please add a number after the command 'done'. Try again.");
                }
            } else { //Should be a valid task to be added
                addToList(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}