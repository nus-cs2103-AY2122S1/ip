import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void start() {
        //Welcome message
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        taskList = load();

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
                throw new DukeException("\t☹ OOPS!!! Please specify the task number for the task " +
                        "you want to complete.");
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

        } else if (commandWord.equals("delete")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! Please specify the task number for the task " +
                        "you want to delete.");
            }
            deleteTask(commandDesc);
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
            taskList.get(taskNumber).markDone();
            save();
            System.out.println("\tNice! I've marked this task as done:\n\t  " + taskList.get(taskNumber));
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! Please input a task number instead.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! This is not a valid task number.");
        }
    }

    /**
     * Lists the tasks the user has currently.
     *
     */
    private static void listTasks() {
        if (!taskList.isEmpty()) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
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
        taskList.add(new ToDo(commandDesc));
        System.out.println("\tGot it. I've added this ToDo:\n" + "\t  " + taskList.get(taskList.size() - 1));
        save();
        printTaskCount();
    }

    /**
     * Add a new Deadline to the user's list of tasks.
     *
     * @param commandDesc the description of the deadline.
     */
    private static void addDeadline(String commandDesc) {
        String[] commandDescSplit = commandDesc.split("/by");
        taskList.add(new Deadline(commandDescSplit[0], commandDescSplit[1]));
        System.out.println("\tGot it. I've added this Deadline:\n" + "\t  " + taskList.get(taskList.size() - 1));
        save();
        printTaskCount();
    }

    /**
     * Add a new Event to the user's list of tasks.
     *
     * @param commandDesc the description of the event.
     */
    private static void addEvent(String commandDesc) {
        String[] commandDescSplit = commandDesc.split("/at");
        taskList.add(new Event(commandDescSplit[0], commandDescSplit[1]));
        System.out.println("\tGot it. I've added this Event:\n" + "\t  " + taskList.get(taskList.size() - 1));
        save();
        printTaskCount();
    }

    /**
     * Deletes a specified task that the user chooses.
     *
     * @param commandDesc the task number in String format that is selected by the user.
     */
    private static void deleteTask(String commandDesc) {
        try {
            int taskNumber = Integer.parseInt(commandDesc) - 1;
            System.out.println("\tNoted. I've removed this task:\n\t  " + taskList.get(taskNumber));
            taskList.remove(taskNumber);
            save();
            printTaskCount();
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! Please input a task number instead.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! This is not a valid task number.");
        }
    }

    private static void save() {
        try {
            File file = new File("data/duke.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            String toSave = "";
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                toSave = toSave.concat(currentTask.taskType()
                        + " | "
                        + currentTask.isDoneToInt()
                        + " | "
                        + currentTask.getTaskDetails() + "\n");
            }

            writer.write(toSave);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Task> load() {
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        try {
            File file = new File("data/duke.txt");
            if (file.createNewFile()) {
                return taskList;
            }

            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(" \\| ");
                if (lineSplit[0].equals("T")) {
                    loadedTaskList.add(new ToDo(lineSplit[2]));
                } else if (lineSplit[0].equals("D")) {
                    loadedTaskList.add(new Deadline(lineSplit[2], lineSplit[3]));
                } else if (lineSplit[0].equals("E")) {
                    loadedTaskList.add(new Event(lineSplit[2], lineSplit[3]));
                }

                if (lineSplit[1].equals("0")) {
                    loadedTaskList.get(loadedTaskList.size() - 1).markDone();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return loadedTaskList;
    }

    /**
     * Prints the number of the tasks the user has currently.
     *
     */
    private static void printTaskCount() {
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
