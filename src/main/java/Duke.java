import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to keep track of Tasks.
 */
public class Duke {

    /**
     * TaskList containing Tasks.
     */
    private TaskList tl;
    private Storage storage;

    /**
     * Constructor for a Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.tl = this.storage.load();
    }

    /**
     * Greetings from Duke.
     */
    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Echoes the given command.
     * @param t User's command.
     */
    private void echo(String t) {
        System.out.println("-----------------------------------------\n" +
                String.format("%s\n", t) +
                "-----------------------------------------\n");
    }

    /**
     * Exit the Duke program.
     */
    private void exit() {
        String exitMessage =
                "-----------------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "-----------------------------------------\n";
        System.out.println(exitMessage);
        System.exit(0);
    }

    /**
     * Adds a task for Duke to track given a parsed command.
     * @param t String array of parsed user input.
     * @throws DukeException Exception for wrong user inputs.
     */
    private void add(String[] t) throws DukeException{
        Task newTask = null;
        switch (t[0]) {
            case "todo":
                if (t[1].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    newTask = new Todo(t);
                }
                break;
            case "deadline":
                if (t[1].equals("") || t[2].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description or deadline of a deadline cannot be empty.");
                } else {
                    newTask = new Deadline(t);
                }
                break;
            case "event":
                if (t[1].equals("") || t[2].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description or scheduled time of an event cannot be empty.");
                } else {
                    newTask = new Event(t);
                }
                break;
        }
        this.tl.addTask(newTask);
        this.storage.write(this.tl);
        System.out.println("-----------------------------------------\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                String.format("Now you have %s tasks in the list.\n", this.tl.getLength()) +
                "-----------------------------------------\n");
    }

    /**
     * Gets the task from duke given an index.
     * @param index Index of Task.
     * @return Task of the given index.
     */
    private Task getTaskByIndex(int index) {
        return this.tl.getTaskByIndex(index);
    }

    /**
     * Marks a task as done given its position in the list.
     * @param itemNum Position of Task in the list.
     */
    private void markDone(int itemNum){
        this.tl.markDone(itemNum);
        this.storage.write(this.tl);
        System.out.println("-----------------------------------------\n" +
                "Nice! I've marked this task as done:\n" +
                this.getTaskByIndex(itemNum - 1).toString() +
                "-----------------------------------------\n");
    }

    /**
     * Deletes a task from the list.
     * @param items Parsed delete command from user.
     * @throws DukeException Exception for wrong user inputs.
     */
    private void deleteTask(String[] items) throws DukeException{
        if (items[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The task's number cannot be empty");
        } else {
            int itemNum = Integer.parseInt(items[1]);
            Task toBeDeleted = this.getTaskByIndex(itemNum - 1);
            String taskName = toBeDeleted.toString();
            this.tl.deleteTask(itemNum - 1);
            this.storage.write(this.tl);
            System.out.println("-----------------------------------------\n" +
                    "Noted. I've removed this task:\n" +
                    taskName +
                    String.format("Now you have %s tasks in the list.\n", this.tl.getLength()) +
                    "-----------------------------------------\n");
        }
    }

    /**
     * Start Duke to allow for inputs.
     */
    private void run() {
        this.greet();
        this.storage.load();
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        String t;
        while (sc.hasNextLine()) {
            t = sc.nextLine();
            String[] items = p.parse(t);

            try {
                switch (items[0]) {
                case "bye":
                    this.exit();
                    break;
                case "list":
                    System.out.println(this.tl.toString());
                    break;
                case "done":
                    this.markDone(Integer.parseInt(items[1]));
                    break;
                case "todo":
                    //Fallthrough
                case "deadline":
                    //Fallthrough
                case "event":
                    this.add(items);
                    break;
                case "delete":
                    this.deleteTask(items);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void main(String[] args){
        Duke duke = new Duke();
        duke.run();
    }
}
