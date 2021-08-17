import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    private static Scanner sc;

    public static void main(String[] args) {
        String logo
                = " _____       _                    _        \n"
                + "|  ___|  __  _  ___   _,____     | |       \n"
                + "| |_  |/  _|| |/ _ \\ |  __  |____| |       \n"
                + "| __| | /   | |  __/ | / \\  |  __  |       \n"
                + "|_|   |_|   |_|\\____ |_|  |_|______|       \n";

        System.out.println("Hi there! Start chatting with your new \n" + logo);
        System.out.println("What would you like to do today?");

        sc = new Scanner(System.in);  // Create a Scanner object

        handleInput();
    }

    private static String printList() {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            listString = listString + index + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).description + "\n";
        }
        return listString;
    }

    private static void handleInput() {

        String message = sc.nextLine();
        String friendGreeting = "(*^_^*) Friend says: \n";

        if (message.equals("bye")) {
            System.out.println(friendGreeting + "See you again my friend!");
            return;
        }
        else if (message.equals("list")) {
            System.out.println(friendGreeting + "Your to-do list has the following tasks: \n");
            System.out.println(printList());
        }
        else if (message.length() > 4 && message.startsWith("done ") && message.substring(5).chars().allMatch(Character::isDigit)) {
            int taskIndex = Integer.parseInt(message.substring(5)) - 1;
            if (0 <= taskIndex && taskIndex < list.size()) {
                Task task = list.get(taskIndex);
                String description = task.description;
                task.markAsDone();
                if (!task.isDone) {
                    System.out.println(friendGreeting + "Hooray! You've completed task \n[X] " + description);
                } else {
                    System.out.println(description + " has already been done! :)");
                }
            } else {
                System.out.println("Sorry, task number " + taskIndex + " cannot be found :(");
            }
        }
        else {
            list.add(new Task(message));
            System.out.println(friendGreeting + "added: " + message + " to your to-do list!");
        }
        handleInput();
    }
}
