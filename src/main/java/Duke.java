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

    private static void addTask(Task t) {
        tasks.add(t);
        printFormattedMessage("Got it. I've added this task:\n\t" 
                                + t.toString() 
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
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

    private static String[] parseTaskWithTime(String command, String type) throws DukeException {
        String splitWord = type == "deadline" ? "/by " : "/at ";
        String[] commandSplit = command.split(splitWord);
        String missingInfo = type == "deadline" ? "a deadline!" : "an event time!";

        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide " + missingInfo + "\n");
        }
        
        String task = commandSplit[0].split(type)[1];
        String time = commandSplit[1];

        String[] result = new String[2];
        result[0] = task;
        result[1] = time;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    printTasks();
                } else if (command.startsWith("done")) {
                    if (command.equals("done")) {
                        throw new DukeException("You need to specify which task you've done!\n");
                    }

                    try {
                        int taskIdx = Integer.parseInt(command.split(" ")[1]) - 1;
                        completeTask(tasks.get(taskIdx));
                    } catch (NumberFormatException err) {
                        throw new DukeException("Please use the task number instead of task name!\n");
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("I'm sorry, but that task number is out of range.\n");
                    }
                } else if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        throw new DukeException("You need to specify which task you want to add!\n");
                    }

                    String todo = command.split("todo")[1];
                    addTask(new ToDo(todo));
                } else if (command.startsWith("deadline") || command.startsWith("event")) {
                    String taskType = command.split(" ")[0];
                    String[] timeTask = parseTaskWithTime(command, taskType);
                    addTask(new Deadline(timeTask[0], timeTask[1]));
                } else {
                    throw new DukeException("I don't understand that command!\n");
                }
            } catch (DukeException e) {
                printFormattedMessage(e.getMessage());
            }
            
            // * Ask user for next command
            System.out.println("What's your next command?\n");
            command = sc.nextLine();         
        }

        printFormattedMessage("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
