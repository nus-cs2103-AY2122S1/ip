import java.util.Scanner;

/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-2
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
    private static void divider() {
        System.out.println("\t" +
                "-------------------------------------");
    }

    //run method to start instance of duke
    private void run() {
        Duke.greeting();

        String req = "";
        boolean end = false;

        do {
            req = this.sc.nextLine();
            switch (req) {
                case "list":
                    Duke.divider();
                    list.getAll();
                    Duke.divider();
                    break;
                case "bye":
                    Duke.goodbye();
                    end = true;
                    break;
                default:
                    Duke.divider();
                    list.add(req);
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
