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
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.startsWith("list")) {
                    printTasks();
                } else if (command.startsWith("done")) {
                    handleTasksOperation(command, "done");
                } else if (command.startsWith("delete")) {
                    handleTasksOperation(command, "delete");
                } else if (command.startsWith("todo")) {
                    addToDo(command);
                } else if (command.startsWith("deadline")) {
                    addTaskWithTime(command, "deadline");
                } else if (command.startsWith("event")) {
                    addTaskWithTime(command, "event");
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

    /**
     * Prints a message with a line of dashes before and after it.
     * 
     * @param message The message to print within 2 lines.
     */
    private static void printFormattedMessage(String message) {
        System.out.println(LINE + "\t" + message + LINE);
    }

    /**
     * Print all the tasks in the ArrayList of tasks.
     */
    private static void printTasks() {
        if (tasks.size() == 0) {
            printFormattedMessage("You don't have any tasks in the list.\n");   
            return;
        }

        String taskListMessage = "I present to you, your collection of tasks!\n\n";
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            String task = "\t" + taskNum+ ". " + tasks.get(i);
            taskListMessage += task + "\n";
        }
        printFormattedMessage(taskListMessage);
    }

    /**
     * Add a task to the ArrayList of tasks.
     * 
     * @param t the task to add
     */
    private static void addTask(Task t) {
        tasks.add(t);
        printFormattedMessage("Got it. I've added this task:\n\t" 
                                + t 
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
    } 

    /**
     * Add ToDo task to the ArrayList of tasks.
     * 
     * @param command user input to extract task
     * @throws DukeException
     */
    private static void addToDo(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("You need to specify which task you want to add!\n");
        }

        String todo = command.split("todo")[1];
        addTask(new ToDo(todo));
    }

    /**
     * Add deadline or event task with date/time.
     * 
     * @param command user input to extract task and date/time
     * @param type    type of task to add
     * @throws DukeException
     */
    private static void addTaskWithTime(String command, String type) throws DukeException {
        boolean isDeadline = type.equals("deadline");
        String splitWord = isDeadline ? "/by " : "/at ";
        String[] commandSplit = command.split(splitWord);   // Split the task from the date/time

        // If cannot split the command, throw an exception
        if (commandSplit.length <= 1) {
            throw new DukeException("You need to provide a date/time!" + "\n");
        }
        
        String task = commandSplit[0].split(type)[1];   // The task is everything after the action
        String time = commandSplit[1];               // The time is the second part of the command

        if (isDeadline) addTask(new Deadline(task, time));
        else addTask(new Event(task, time));
    }

    /**
     * Mark a task as done.
     * 
     * @param taskNum the task number to mark as done
     */
    private static void completeTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.setDone(true);
        printFormattedMessage("Good job! I've marked this task as done:\n\n\t" + task + "\n");  
    }

    /**
     * Delete a task from the ArrayList of tasks.
     * 
     * @param num the task number to delete
     */
    private static void deleteTask(int num) {
        int taskIdx = num - 1;
        Task taskToDelete = tasks.get(taskIdx);
        tasks.remove(taskIdx);
        printFormattedMessage("Noted. I've removed this task:\n\t" 
                                + taskToDelete
                                + "\n\tNow you have " + tasks.size() + " tasks in the list.\n"); 
    } 

    /**
     * Delete or mark a task as done.
     * 
     * @param command user input to extract task number
     * @param action delete or mark as done
     * @throws DukeException
     */
    private static void handleTasksOperation(String command, String action) throws DukeException {
        if (command.equals(action)) {
            throw new DukeException("You need to specify the task!\n");
        }

        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (action.equals("done")) completeTask(taskNum);
            else deleteTask(taskNum);
        } catch (NumberFormatException err) {
            throw new DukeException("Please use the task number instead of task name!\n");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("I'm sorry, but that task number is out of range.\n");
        }
    }
}
