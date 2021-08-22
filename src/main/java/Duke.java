import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean inSession;
    private static final ArrayList<Task> toDoList = new ArrayList<>();
    private static final String addedMessage = "Got it. I've added this task:\n";

    /**
     * Starts Duke.
     */
    public static void start() {
        inSession = true;
        greet();
        loadData();
        Scanner sc = new Scanner(System.in);
        while (inSession) {
            String input = sc.nextLine();
            String action = input.split(" ", 2)[0].toLowerCase();
            switch (action) {
            case ("bye"):
                exit();
                break;
            case ("list"):
                displayList();
                break;
            case ("done"):
                try {
                   markAsDone(input);
                   saveData();
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case ("delete"):
                try {
                    delete(input);
                    saveData();
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case ("todo"):
                try {
                    addTodo(input);
                    saveData();
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case ("deadline"):
                try {
                    addDeadline(input);
                    saveData();
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case ("event"):
                try {
                    addEvent(input);
                    saveData();
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            default:
                unknownCommand();
            }
        }
    }

    /**
     * Prints a greeting message.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Stops and exits Duke.
     */
    public static void exit() {
        inSession = false;
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Adds a Todo task into the toDoList.
     *
     * @param input input String by the user.
     * @throws DukeException is thrown when there is no description for the task.
     */
    public static void addTodo(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(info[1]);
        toDoList.add(newTodo);
        System.out.println(addedMessage + newTodo.toString() +
                "\nNow you have " + toDoList.size() + " tasks in the list.");
    }

    /**
     * Adds a Deadline task into the toDoList.
     *
     * @param input input String by the user.
     * @throws DukeException is thrown when there is no description
     * or deadline for the task.
     */
    public static void addDeadline(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of a deadline cannot be empty.");
        }
        String[] description = info[1].split("/by ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The deadline of a deadline cannot be empty.");
        }
        Deadline newDeadline = new Deadline(description[0], description[1]);
        toDoList.add(newDeadline);
        System.out.println(addedMessage + newDeadline.toString() +
                "\nNow you have " + toDoList.size() + " tasks in the list.");
    }

    /**
     * Adds an Event task into the toDoList.
     *
     * @param input input String by the user.
     * @throws DukeException is thrown when there is no description
     * or date for the task.
     */
    public static void addEvent(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of an event cannot be empty.");
        }
        String[] description = info[1].split("/at ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The date of an event cannot be empty.");
        }
        Event newEvent = new Event(description[0], description[1]);
        toDoList.add(newEvent);
        System.out.println(addedMessage + newEvent.toString() +
                "\nNow you have " + toDoList.size() + " tasks in the list.");
    }

    /**
     * Prints the current toDoList.
     */
    public static void displayList() {
        String listString = "Here are the tasks in your list:";
        for (int i = 0; i < toDoList.size(); i++) {
            listString += "\n" + (i + 1) + "." + toDoList.get(i).toString();
        }
        System.out.println(listString);
    }

    /**
     * Marks a current task as done.
     *
     * @param input input String by the user.
     * @throws DukeException is thrown when there is no taskNumber provided
     * or an invalid taskNumber is provided.
     */
    public static void markAsDone(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter the task number you would like to mark as done.");
        }
        int i;
        try {
            i = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter an integer for the task you would like to mark as done.");
        }
        if (i > toDoList.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + i + ".");
        }
        Task t = toDoList.get(i - 1);
        t.setDone();
        System.out.println("Nice! I've marked this task as done:\n"
                            + "  " + t.toString());
    }

    /**
     * Deletes a task from the toDoList.
     *
     * @param input input String by the user.
     * @throws DukeException is thrown when there is no taskNumber provided
     * or an invalid taskNumber is provided.
     */
    public static void delete(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter the task number you would like to delete.");
        }
        int i;
        try {
            i = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter an integer for the task you would like to delete.");
        }
        if (i > toDoList.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + i + ".");
        }
        Task t = toDoList.remove(i - 1);
        System.out.println("Noted! I've removed this task:\n" + "  "
                + t.toString() + "\nNow you have " + toDoList.size()  + " tasks in the list.");
    }

    /**
     * Prints an error message when an unknown command is given.
     */
    public static void unknownCommand() {
        System.err.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Loads data from the dataFile.
     * If there is no dataFile, creates one.
     */
    public static void loadData() {
        // creates a folder for the datafile if there is none.
        File folder = new File("./data/");
        if (folder.mkdir()) {
            System.out.println("A data folder is created.");
        }

        try {
            File dataFile = new File("./data/duke.txt");
            if (dataFile.createNewFile()) {
                System.out.println("A data file is created for saving your data.");
            } else {
                System.out.println("Loading your previous data...");
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    toDoList.add(stringToTask(taskString));
                }
                sc.close();
                System.out.println("Successfully loaded your data!");
            }
        } catch (IOException e) {
            System.err.println("There is an error in creating the data file.");
        }
    }

    /**
     * Saves data to the dataFile.
     */
    public static void saveData() {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task t : toDoList) {
                writer.write(t.toString() + "\n");
            }
            writer.close();
            System.out.println("Task saved!");
        } catch (IOException e2) {
            System.err.println("There is an error in saving the data.");
        }
    }

    /**
     * Converts the String of a task into a Task.
     * For reading saved data from the dataFile.
     *
     * @param taskString the String of a task, taken from the data file.
     * @return the Task as described by the taskString.
     */
    public static Task stringToTask(String taskString) {
        Task result = new Task();
        String taskType = taskString.substring(0, 3);
        String taskStatus = taskString.substring(3, 6);
        String taskDetail = taskString.substring(7);
        switch (taskType) {
        case ("[T]"):
            result = new Todo(taskDetail);
            if (taskStatus.equals("[X]")) {
                result.setDone();
            }
            return result;
        case ("[D]"):
            String[] deadlineDescription = taskDetail
                    .substring(0, taskDetail.length() - 1)
                    .split("by: ", 2);
            result = new Deadline(deadlineDescription[0]
                    .substring(0, deadlineDescription[0].length() - 2), deadlineDescription[1]);
            if (taskStatus.equals("[X]")) {
                result.setDone();
            }
            return result;
        case ("[E]"):
            String[] eventDescription = taskDetail
                    .substring(0, taskDetail.length() - 1)
                    .split("at: ", 2);
            result = new Event(eventDescription[0]
                    .substring(0, eventDescription[0].length() - 2), eventDescription[1]);
            if (taskStatus.equals("[X]")) {
                result.setDone();
            }
            return result;
        default:
            return result;
        }
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
