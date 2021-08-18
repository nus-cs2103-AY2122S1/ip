import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static String logo = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String lines = "\t----------------------------------------------------\n";
    private final static String greetings = "Greetings friend! I am your personal assistant,\n" + 
                                            logo + 
                                            "\nWhat can I do for you?\n";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Prints a message with a line of dashes before and after it.
     */
    public static void printFormattedMessage(String message) {
        System.out.println(lines + "\t" + message + lines);
    }

    public static void main(String[] args) {
        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            // * Print list of tasks if command is "list"
            if (command.equals("list")) {
                String taskListMessage = "I present to you, your collection of tasks!\n\n";
                for (int i = 0; i < tasks.size(); i++) {
                    int taskNum = i + 1;
                    String t = "\t" + taskNum + ". " + tasks.get(i);
                    taskListMessage += t + "\n";
                }
                printFormattedMessage(taskListMessage);
            } else {
                // * For any other text input, add a task to the list
                tasks.add(new Task(command));
                printFormattedMessage("added: " + command + "\n"); 
            }
            
            // * Ask user for next command
            command = sc.nextLine();         
        }

        printFormattedMessage("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
