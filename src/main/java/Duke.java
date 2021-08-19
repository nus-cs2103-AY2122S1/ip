import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static void start() {
        //Welcome message
        System.out.println("Hello I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean end = false;

        //Main functionality of Duke
        while(!end) {
            String command = "";
            if(sc.hasNextLine()) {
                command = sc.nextLine().trim();
            }
            try {
                if (listen(command) == 1) {
                    end = true;
                    System.out.println("\tBye. Hope to see you again soon!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Listens to text command from the user and takes action accordingly.
     *
     * @param command command entered by the user
     * @return an integer 0 or 1, where 1 represents the user exiting the bot.
     * @throws DukeException if the user inputs any incorrect commands.
     */
    private static int listen(String command) throws DukeException{
        String[] commandSplit = command.split(" ", 2);
        String commandWord = commandSplit[0].toLowerCase();
        String commandDesc = "";
        if (commandSplit.length == 2) {
            commandDesc = commandSplit[1];
        }

        if (commandWord.equals("done")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! Please specify the task number for the task you want to complete.");
            }
            markDone(commandDesc);

        } else if (commandWord.equals("list")) {
            listTasks();

        } else if (commandWord.equals("todo")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            addToDo(commandDesc);

        } else if (commandWord.equals("deadline")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            addDeadline(commandDesc);

        } else if (commandWord.equals("event")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
            }
            addEvent(commandDesc);

        } else if (commandWord.equals("bye")) {
            return 1;
        } else {
            throw new DukeException("\t☹ OOPS!!! You have entered an invalid command, please try again.");
        }
        return 0;
    }

    /**
     * Marks the Task specified by the user as done.
     *
     * @param commandDesc the String format of the task number
     */
    private static void markDone(String commandDesc) {
        try {
            int taskNumber = Integer.parseInt(commandDesc) - 1;
            if (taskNumber >= taskCount) {
                throw new DukeException("\t☹ OOPS!!! This is not a valid task number.");
            }
            taskList[taskNumber].markDone();
            System.out.println("\tNice! I've marked this task as done:\n\t  " + taskList[taskNumber]);
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! Please input a task number instead.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Lists the tasks the user has currently.
     *
     */
    private static void listTasks() {
        if (taskCount != 0) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList[i]);
            }
        } else {
            System.out.println("\tYou don't have any tasks in your list!");
        }

    }

    /**
     * Add a new ToDo Task to the user's list of tasks.
     *
     * @param commandDesc the description of the task.
     */
    private static void addToDo(String commandDesc){
        taskList[taskCount] = new ToDo(commandDesc);
        System.out.println("\tGot it. I've added this ToDo:\n" + "\t  " + taskList[taskCount]);
        taskCount++;
        printTaskCount();
    }

    /**
     * Add a new Deadline to the user's list of tasks.
     *
     * @param commandDesc the description of the deadline.
     */
    private static void addDeadline(String commandDesc) {
        String[] commandDescSplit = commandDesc.split("/by");
        taskList[taskCount] = new Deadline(commandDescSplit[0], commandDescSplit[1]);
        System.out.println("\tGot it. I've added this Deadline:\n" + "\t  " + taskList[taskCount]);
        taskCount++;
        printTaskCount();
    }

    /**
     * Add a new Event to the user's list of tasks.
     *
     * @param commandDesc the description of the event.
     */
    private static void addEvent(String commandDesc) {
        String[] commandDescSplit = commandDesc.split("/at");
        taskList[taskCount] = new Event(commandDescSplit[0], commandDescSplit[1]);
        System.out.println("\tGot it. I've added this Event:\n" + "\t  " + taskList[taskCount]);
        taskCount++;
        printTaskCount();
    }

    /**
     * Prints the number of the tasks the user has currently.
     *
     */
    private static void printTaskCount() {
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
