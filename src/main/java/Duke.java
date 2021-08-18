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
            listString = listString + index + "." + list.get(i).toString() + "\n";
        }
        return listString;
    }

    private static void handleInput() {

        while (sc.hasNextLine()) {

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
                    if (!task.isDone) {
                        task.markAsDone();
                        System.out.println(friendGreeting + "Hooray! You've completed task \n[X] " + description);
                    } else {
                        System.out.println(description + " has already been done! :)");
                    }
                } else {
                    System.out.println("Sorry, task number " + taskIndex + " cannot be found :(");
                }
            }
            else {
                // To Do
                if (message.length() > 5 && message.startsWith("todo ")) {
                    String description = message.substring(5);
                    list.add(new ToDo(description));
                }
                // deadline
                else if (message.length() > 9 && message.startsWith("deadline ") && message.contains(" /by ")) {
                    String description = message.substring(9, message.indexOf("/by") - 1);
                    String by = message.substring(message.indexOf("/by") + 4);
                    list.add(new Deadline(description, by));
                }
                // event
                else if (message.length() > 6 && message.startsWith("event ") && message.contains(" /at ")) {
                    String description = message.substring(6, message.indexOf("/at") - 1);
                    String at = message.substring(message.indexOf("/at") + 4);
                    list.add(new Event(description, at));
                }

                System.out.println(friendGreeting + "added: " + list.get(list.size() - 1).toString() + " to your to-do list!");
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }

            handleInput();

        }
    }
}
