import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String LINE = "\t----------------------------------------------------\n";
    private final static String GREETING = "Greetings friend! I am your personal assistant,\n" + 
                                            LOGO + 
                                            "\nWhat can I do for you?\n";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Prints a message with a line of dashes before and after it.
     */
    private static void printFormattedMessage(String message) {
        System.out.println(LINE + "\t" + message + LINE);
    }

    /**
     * Prompt user for next command after printing a formatted message.
     */
    private static String promptNextCommand(String message, Scanner sc) {
        printFormattedMessage(message);
        return sc.nextLine();
    }

    private static void addTask(Task t) {
        tasks.add(t);
        printFormattedMessage("added: " + t.getName() + "\n"); 
    } 

    private static void printTasks() {
        String taskListMessage = "I present to you, your collection of tasks!\n\n";
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            String t = "\t" + taskNum+ ". " + tasks.get(i);
            taskListMessage += t + "\n";
        }
        printFormattedMessage(taskListMessage);
    }

    private static void completeTask(Task t) {
        t.setDone(true);
        printFormattedMessage("Good job! I've marked this task as done:\n\n\t" + t + "\n");  
    }

    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            // * Print list of tasks if command is "list"
            String[] splitCommand = command.split(" ");
            String action = splitCommand[0];

            switch(action) {
                case "list":
                    printTasks();
                    break;
                case "done":
                    if (splitCommand.length <= 1) {
                        command = promptNextCommand("You need to specify which task you've done!\n", sc);
                        continue;
                    }

                    int taskIdx = Integer.parseInt(splitCommand[1]) - 1;
                    if (taskIdx >= tasks.size() || taskIdx < 0) {
                        command = promptNextCommand("I'm sorry, but that task number is out of range.\n", sc);
                        continue;
                    }
                    
                    completeTask(tasks.get(taskIdx));
                    break;
                default:
                    // * For any other text input, add a task to the list if it is not empty
                    if (command.length() > 0) {
                        addTask(new Task(command));
                    } 
            }
            
            // * Ask user for next command
            System.out.println("What's your next command?\n");
            command = sc.nextLine();         
        }

        printFormattedMessage("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
