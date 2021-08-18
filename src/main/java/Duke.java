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
                                + t 
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
    } 

    private static void deleteTask(int num) {
        int taskIdx = num - 1;
        Task taskToDelete = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        printFormattedMessage("Noted. I've removed this task:\n\t" 
                                + taskToDelete
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

    private static void completeTask(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        t.setDone(true);
        printFormattedMessage("Good job! I've marked this task as done:\n\n\t" + t + "\n");  
    }

    private static void handleTaskWithTime(String command, String type) throws DukeException {
        String splitWord = type == "deadline" ? "/by " : "/at ";
        String[] commandSplit = command.split(splitWord);
        String missingInfo = type == "deadline" ? "a deadline!" : "an event time!";

        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide " + missingInfo + "\n");
        }
        
        String task = commandSplit[0].split(type)[1];
        String time = commandSplit[1];

        if (type == "deadline") {
            addTask(new Deadline(task, time));
        } else {
            addTask(new Event(task, time));
        }
    }

    private static void handleTasksOperation(String command, String action) throws DukeException {
        if (command.equals(action)) throw new DukeException("You need to specify the task!\n");

        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (action.equals("done")) {
                completeTask(taskNum);
            } else {
                deleteTask(taskNum);
            }
        } catch (NumberFormatException err) {
            throw new DukeException("Please use the task number instead of task name!\n");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("I'm sorry, but that task number is out of range.\n");
        }
    }

    private static void handleToDo(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("You need to specify which task you want to add!\n");
        }

        String todo = command.split("todo")[1];
        addTask(new ToDo(todo));
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
                    handleTasksOperation(command, "done");
                } else if (command.startsWith("delete")) {
                    handleTasksOperation(command, "delete");
                } else if (command.startsWith("todo")) {
                    handleToDo(command);
                } else if (command.startsWith("deadline")) {
                    handleTaskWithTime(command, "deadline");
                } else if (command.startsWith("event")) {
                    handleTaskWithTime(command, "event");
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
