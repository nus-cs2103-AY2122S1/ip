import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke(Hiko created by me) is a personal assistant.
 *
 * @author Chen Yanyu
 */
public class Duke {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final List<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                handleInput(sc.nextLine());
            } catch (DukeException e) {
                errorPrint(e.getMessage());
            }
        }
    }

    private static void greet() {
        String logo =
                " _   _ _ _\n" +
                        "| | | (_) | _____\n" +
                        "| |_| | | |/ / _ \\\n" +
                        "|  _  | |   < (_) |\n" +
                        "|_| |_|_|_|\\_\\___/\n";

        hikoPrint("Hello from\n" + logo + "What can I do for you?\n");
    }

    private static void hikoPrint(String str) {
        System.out.print(ANSI_PURPLE + str + ANSI_RESET + "\n> ");
    }

    private static void errorPrint(String err) {
        System.out.print(ANSI_RED + err + ANSI_RESET + "\n> ");
    }

    private static void handleInput(String input)
            throws UnknownActionException, EmptyDescriptionException, WrongFormatException, ListIndexException {
        Command command = new Command(input);
        switch (command.getAction()) {
            case BYE:
                handleBye();
                break;
            case LIST:
                handleList();
                break;
            case DONE:
                handleMarkDone(command.getArgument());
                break;
            case DELETE:
                handleDelete(command.getArgument());
                break;
            case TODO:
                Task task = new Todo(command.getArgument());
                handleAdd(task);
                break;
            case DEADLINE:
                Task deadline = new Deadline(command.getArgument());
                handleAdd(deadline);
                break;
            case EVENT:
                Task event = new Event(command.getArgument());
                handleAdd(event);
                break;
            default:
                throw new UnknownActionException();
        }
    }

    private static void handleDelete(String arg) throws WrongFormatException, ListIndexException {
        try {
            int idx = Integer.parseInt(arg);
            Task task = TASKS.remove(idx - 1);
            hikoPrint("Noted. I've removed this task:\n  " +
                    task +
                    "\nNow you have " + TASKS.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new WrongFormatException("delete <index for the task>");
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexException();
        }
    }

    private static void handleMarkDone(String arg) throws WrongFormatException, ListIndexException {
        try {
            int idx = Integer.parseInt(arg);
            Task task = TASKS.get(idx - 1);
            task.setDone();
            hikoPrint("Nice! I've marked this task as done:\n" + task + "\n");
        } catch (NumberFormatException e) {
            throw new WrongFormatException("done <index for the task>");
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexException();
        }

    }

    private static void handleAdd(Task task) {
        TASKS.add(task);
        String msg = "Got it. I've added this task:\n  " +
                task +
                "\nNow you have " + TASKS.size() + " tasks in the list.\n";

        hikoPrint(msg);
    }

    private static void handleList() {
        String output = "";
        if (TASKS.isEmpty()) {
            output = "Hey! You have not added any task...\n";
        } else {
            output = "Here are the tasks in your list:\n";
            for (int i = 1; i <= TASKS.size(); i++) {
                Task task = TASKS.get(i - 1);
                output += i + ". " + task + "\n";
            }
        }
        hikoPrint(output);
    }

    private static void handleBye() {
        System.out.println(ANSI_PURPLE + "Bye! Hope to see you again soon!" + ANSI_RESET);
        System.exit(0);
    }
}
