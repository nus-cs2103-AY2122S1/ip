import java.util.Scanner;
/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-4
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
        System.out.println("\t" +
                "-------------------------------------");
    }

    //Class method to print greeting
    private static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        Duke.divider();
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        Duke.divider();
    }

    //Class method to print goodbye
    private static void goodbye() {
        Duke.divider();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        Duke.divider();
    }

    //done method to mark a task as done
    private void done(String req) {
        int index = Integer.parseInt(req);
        Task task = this.list.getIndex(index);
        task.markAsDone();
    }

    private void addToDo(String req) {
        ToDo todo = new ToDo(req);
        this.list.add(todo);
    }

    private void addDeadline(String req) {
        String[] splitReq = req.split("/by", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Deadline deadline = new Deadline(desc, date);
        this.list.add(deadline);
    }

    private void addEvent(String req) {
        String[] splitReq = req.split("/at", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Event event = new Event(desc, date);
        this.list.add(event);
    }

    //run method to start instance of duke
    private void run() {
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
                    this.addToDo(splitReq[1]);
                    break;

                case "deadline":
                    this.addDeadline(splitReq[1]);
                    break;

                case "event":
                    this.addEvent(splitReq[1]);
                    break;

                default:
                    Duke.divider();
                    System.out.println("\tSorry! I do not understand you? Try another command!");
                    Duke.divider();
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
