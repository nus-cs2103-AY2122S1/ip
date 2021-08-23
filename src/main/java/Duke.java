import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-6
 */
public class Duke {
    public static final String divider = "-------------------------------------";
    private Scanner sc;
    private List list;

    //Constructor
    public Duke(Scanner sc) {
        this.sc = sc;
        this.list = new List();
    }

    /** Prints greeting statement */
    private static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println(Duke.divider);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(Duke.divider);
    }

    /** Prints goodbye statement */
    private static void goodbye() {
        System.out.println(Duke.divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.divider);
    }

    /** Adds a ToDo to the list */
    private void addToDo(String req) throws NoDescriptionException {
        if (req.equals("todo")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String[] splitReq = req.split(" ", 2);
        String body = splitReq[1];
        ToDo todo = new ToDo(body); //Could also throw and error in the constructor
        this.list.add(todo);
    }

    /** Adds a Deadline to the list */
    private void addDeadline(String req) throws NoDescriptionException, MissingTimeCommandException{
        if (req.equals("deadline")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!req.contains("/by")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/by' in the command.");
        }

        String[] splitReq = req.split("/by", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Deadline deadline = new Deadline(desc, date);
        this.list.add(deadline);

    }

    /** Adds an Event to the list */
    private void addEvent(String req) throws NoDescriptionException, MissingTimeCommandException {
        if (req.equals("event")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (!req.contains("/at")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/at' in the command.");
        }

        String[] splitReq = req.split("/at", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Event event = new Event(desc, date);
        this.list.add(event);
    }

    /** Receives an invalid command and throws and exception*/
    private void invalidInput(String req) throws InvalidCommandException {
      throw new InvalidCommandException(
        "Sorry! I do not understand you? Try another command!");
    }

    /** Receives an index to specify a task is done */
    private void done(String req) throws NoDescriptionException, InvalidDescriptionException {
        if (req.equals("done")) {
            throw new NoDescriptionException("Please specify the task number.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];
        int index;

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'done'.");
        }

        Task task = this.list.getIndex(index);
        task.markAsDone();
    }

    /** Receives an index to specify a task to delete*/
    private void deleteTask(String req) throws NoDescriptionException, InvalidDescriptionException {
        if (req.equals("delete")) {
            throw new NoDescriptionException("Please indicate a task to delete.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];
        int index;

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'delete'.");
        }

        this.list.removeTask(index);
    }

    /** Runs the instance of duke*/
    private void run() {
        Duke.greet();

        String req = "";
        boolean end = false;

        do {
            try {
                //Get first command word
                req = this.sc.nextLine();
                String[] splitReq = req.split(" ", 2);
                String cmd = splitReq[0];

                //Switch statement based on initial command
                switch (cmd) {
                    case "list":
                        this.list.getAll();
                        break;

                    case "bye":
                        Duke.goodbye();
                        end = true;
                        break;

                    case "done":
                        this.done(req);
                        break;

                    case "todo":
                        this.addToDo(req);
                        break;

                    case "deadline":
                        this.addDeadline(req);
                        break;

                    case "event":
                        this.addEvent(req);
                        break;

                    case "delete":
                        this.deleteTask(req);
                        break;

                    default:
                        this.invalidInput(req);
                }
            } catch (Exception e) {
                this.exceptionHandler(e);
            }
        } while (!end);
        sc.close();
    }

    /** Catches all exceptions from run. Should the param be  of type DukeException? */
    private void exceptionHandler(Exception e) {
        System.err.println(e.getMessage());
        //enumerate exceptions here
        //switch cases for exceptions
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke(sc);
        duke.run();
    }
}
