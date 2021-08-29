import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    public Ui() {}

    public void welcomeMessage() {
        System.out.println("Welcome to Ben's. How may I help you?");
    }

    public static void printTasksOnLoad(Scanner currList) {
        System.out.println("Current list:");
        while (currList.hasNext()) {
            System.out.println(currList.nextLine());
        }
    }

    public static void firstTimeMessage() {
        System.out.println("\nNote: This is your first time using Duke. " +
                "We will now create a new file for you.");
    }

    public static void importantMessage() {
        System.out.println("\nImportant notes: \n1) For deadlines, it must contain /by ... and events must contain" +
                " /at ...");
        System.out.println("2) The ... must be in the form DATE TIME where the DATE must follow this format:" +
                "YYYY-MM-DD;\n   and TIME must follow the 24hr format (e.g. 2pm = 1400)\n");
    }

    public static void emptyListMessage() {
        System.out.println("    ***\n" + "    There is currently nothing in the list. \n" +
                "    ***\n");
    }

    public static void getCurrentTasks(ArrayList<Task> contents) {
        int counter = 1;
        System.out.println("    ***\n" + "    These are your tasks in the list:");
        for (Task x: contents) {
            System.out.println("      " + counter + ". " + x.getStatusIcon() + " " +
                    x.getDescription());
            counter++;
        }
        System.out.println("    ***\n");
    }

    public static void markedTask(Task task) {
        System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n");
    }

    public static void addedTask(Task task, int count) {
        if (count == 1) {
            System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " task in the list.\n" +
                    "    ***\n");
        } else {
            System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " tasks in the list.\n" +
                    "    ***\n");
        }
    }

    public static void removedTask(Task task) {
        System.out.println("    ***\n" + "    You have successfully removed this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n");
    }

    public static void invalidTask() {
        System.out.println("Invalid input. \nYou may only use the following inputs: " +
                "(bye, list, done, deadline, event, todo) and any text thereafter.");
        importantMessage();
    }

    public static void successfulWriteFile() {
        System.out.println("Successfully written contents to file.");
    }

    public static void failedWriteFile(String error) {
        System.out.println("Failed to write to the file. Error: " + error);
    }

    public static void goodbyeMessage() {
        System.out.println("\nGoodbye! Have a nice day. :)");
    }
}
