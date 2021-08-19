import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-6
 */
public class Duke {
    private Scanner sc;
    private List list;

    //Constructor
    public Duke(Scanner sc) {
        this.sc = sc;
        this.list = new List();
    }

    //Class method to print static line
    public static void divider() {
        System.out.println("-------------------------------------");
    }

    //Class method to print greeting
    private static void greeting() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        Duke.divider();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke.divider();
    }

    //Class method to print goodbye
    private static void goodbye() {
        Duke.divider();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.divider();
    }

    //done method to mark a task as done
    private void done(String req) {
        int index = Integer.parseInt(req);
        Task task = this.list.getIndex(index);
        task.markAsDone();
    }

    //addToDo method throws an exception if there is no description
    private void addToDo(String req) throws NoSuchElementException {
        if (req.equals("todo")) {
            throw new NoSuchElementException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String[] splitReq = req.split(" ", 2);
        String body = splitReq[1];
        ToDo todo = new ToDo(body); //Could also throw and error in the constructor
        this.list.add(todo);
    }

    //addDeadline method
    private void addDeadline(String req) {
        String[] splitReq = req.split("/by", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Deadline deadline = new Deadline(desc, date);
        this.list.add(deadline);
    }

    //addEvent method
    private void addEvent(String req) {
        String[] splitReq = req.split("/at", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Event event = new Event(desc, date);
        this.list.add(event);
    }

    //wrongInput method called from the default switch statement in this.run()
    private void wrongInput(String req) throws IllegalArgumentException {
      throw new IllegalArgumentException(
        "Sorry! I do not understand you? Try another command!");
    }

    //deleteTask method throws error if no number is appended to command
    private void deleteTask(String req) throws NoSuchElementException {
        if (req.equals("delete")) {
            throw new NoSuchElementException("☹ OOPS!!! Please indicate a task to delete!");
        }
        String[] splitReq = req.split(" ", 2);
        int index = Integer.parseInt(splitReq[1]);
        this.list.removeTask(index);
    }

    //run method to start instance of duke
    private void run() throws IllegalArgumentException {
        Duke.greeting();

        String req = "";
        boolean end = false;

        do {
            //Get first command
            req = this.sc.nextLine();
            String[] splitReq = req.split(" ", 2);
            String cmd = splitReq[0];

            //Switch statement based on initial command
            switch (cmd) {
                case "list":
                    Duke.divider();
                    this.list.getAll();
                    Duke.divider();
                    break;

                case "bye":
                    Duke.goodbye();
                    end = true;
                    break;

                case "done":
                    this.done(splitReq[1]);
                    break;

                case "todo":
                    try {
                        this.addToDo(req);
                    } catch (NoSuchElementException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case "deadline":
                    this.addDeadline(splitReq[1]);
                    break;

                case "event":
                    this.addEvent(splitReq[1]);
                    break;

                case "delete":
                    try {
                        this.deleteTask(req);
                    } catch (NoSuchElementException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                default:
                    try {
                        this.wrongInput(req);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
            }
        } while (!end);
        sc.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke(sc);
        duke.run();
    }
}
