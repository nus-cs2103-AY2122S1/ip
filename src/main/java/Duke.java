import java.util.Scanner;

/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-3
 */
public class Duke {
    private Scanner sc;
    private List list;

    //Constructor
    public Duke(Scanner sc) {
        this.sc = sc;
        this.list = new List();
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

    //Class method to print static line
    public static void divider() {
        System.out.println("\t" +
                "-------------------------------------");
    }

    //run method to start instance of duke
    private void run() {
        Duke.greeting();

        String req = "";
        boolean end = false;

        do {
            //Get the first command
            req = this.sc.next();
            switch (req) {
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
                    int index = sc.nextInt(); //catch an error here
                    Task doneTask = this.list.getIndex(index);
                    doneTask.markAsDone();

                    Duke.divider();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t\t" + doneTask.toString());
                    Duke.divider();
                    break;

                default:
                    Task task = new Task(req + sc.nextLine());
                    Duke.divider();
                    list.add(task);
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
