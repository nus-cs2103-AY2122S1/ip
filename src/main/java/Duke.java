import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String NAME = "Tze Henn";
    private static final ArrayList<Task> list = new ArrayList<>();

    enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    /** Prints out a long line. */
    public static void lineGenerator() {
        System.out.println("____________________________________________________________");
    }

    /** Prints out all user's tasks in numerical order. */
    public static void taskCommand() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input input given by the user starting with "done".
     */
    public static void doneCommand(String input) {
        System.out.println("Nice! I've marked this task as done: ");
        String[] splitStr = input.split("\\s+");
        System.out.print("  ");
        list.get(Integer.parseInt(splitStr[1]) - 1).markTaskDone();
    }

    /**
     * Deletes a task from the list.
     *
     * @param input input given by the user starting with "delete" and the corresponding task number to delete.
     */
    public static void deleteCommand(String input) {
        System.out.println("Noted! I've removed this task: ");
        String[] splitStr = input.split("\\s+");
        System.out.print("  ");
        System.out.println(list.get(Integer.parseInt(splitStr[1]) - 1));
        list.remove(Integer.parseInt(splitStr[1]) - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Returns a Todo object with the corresponding todo item.
     *
     * @param input input given by the user starting with "todo" and the corresponding todo item.
     * @return A Todo object.
     * @throws DukeException If the description of todo is empty.
     */
    public static Todo todoCommand(String input) throws DukeException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
        }
        return new Todo(description);
    }

    /**
     * Returns a Deadline object with the corresponding deadline item.
     *
     * @param input input given by the user starting with "deadline" and the corresponding deadline item.
     * @return A Deadline object.
     */
    public static Deadline deadlineCommand(String input) {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("deadline".length() + 1, slashPosition);
            String by = input.substring(slashPosition + 4);
            String[] splitStr = by.split("\\s+");
            return new Deadline(description, timeFormatter(splitStr[1]), dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of" +
                    " dd-mm-yyyy hhmm");
        }

    }

    /**
     * Returns a Event object with the corresponding event item.
     *
     * @param input input given by the user starting with "event" and the corresponding event item.
     * @return A Event object.
     */
    public static Event eventCommand(String input) throws DukeException {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("event".length() + 1, slashPosition);
            String at = input.substring(slashPosition + 4);
            String[] splitStr = at.split("\\s+");
            return new Event(description, timeFormatter(splitStr[1]), dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of" +
                    "dd-mm-yyyy hhmm.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */

    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task: ");
        list.add(t);
        System.out.println("  " + t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /** Signals the end of the program */
    private static void byeCommand() {
        lineGenerator();
        System.out.println("Bye. Hope to see you again soon!");
        lineGenerator();
    }

    public static LocalDate dateFormatter(String str) throws DukeException {
        try {
            String[] splitStr = str.split("/");
            String day = String.format("%1$" + 2 + "s", splitStr[0]).replace(' ', '0');
            String month = String.format("%1$" + 2 + "s", splitStr[1]).replace(' ', '0');
            return LocalDate.parse(splitStr[2] + "-" +  month + "-" + day);
        } catch (Exception e) {
            throw new DukeException("Your Date is wrongly formatted! It should be in the form of dd-mm-yyyy.");
        }
    }

    public static LocalTime timeFormatter(String str) throws DukeException {
        try {
            return LocalTime.parse(str.substring(0, 2) + ":" + str.substring(2));
        } catch (Exception e) {
            throw new DukeException("Your Time is wrongly formatted! It should be ineve the form of hhmm.");
        }
    }

    /** Start of the program */
    public static void run() {
        try {
            lineGenerator();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("Hello! I'm " + NAME);
            System.out.println("What can I do for you?");
            lineGenerator();

            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter command: ");
            String input = sc.nextLine();


            while (!input.equals(Commands.BYE.toString().toLowerCase())) {
                lineGenerator();
                Task t;
                if (input.equals(Commands.LIST.toString().toLowerCase())) {
                    taskCommand();
                } else if (input.startsWith(Commands.DONE.toString().toLowerCase())) {
                    doneCommand(input);
                } else if (input.startsWith(Commands.DELETE.toString().toLowerCase())) {
                    deleteCommand(input);
                } else {
                    if (input.startsWith(Commands.TODO.toString().toLowerCase())) {
                        t = todoCommand(input);
                    } else if (input.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
                        t = deadlineCommand(input);
                    } else if (input.startsWith(Commands.EVENT.toString().toLowerCase())) {
                        t = eventCommand(input);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    addTask(t);
                }
                lineGenerator();
                System.out.print("\nEnter command: ");
                input = sc.nextLine();
            }
            byeCommand();
        } catch (DukeException e) {
            System.out.println(e);
            lineGenerator();
        }
    }

    public static void main(String[] args) {
        run();
    }
}
