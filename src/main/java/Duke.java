import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private String name;
    private String exitMsg = "Bye. Hope to see you again soon!";

    /**
     * Class constructor specifying the name of the Chatbot to be created.
     *
     * @param name Name of the Chatbot.
     */
    public Duke(String name) {
        this.name = name;
    }

    /**
     * Print the greeting message of the Chatbot.
     */
    private void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm " + this.name);
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Echoes whatever is passed into <code>input</code> as argument with formatting.
     *
     * @param input The message to be printed on console.
     */
    private void echo(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Method to start the Chatbot and listen for user input.
     *
     * @param sc Scanner object to listen for user input.
     */
    public void start(Scanner sc) {
        this.greet();

        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    this.echo(this.exitMsg);
                    return;
                default:
                    this.echo(input);
            }
        }
    }

    /**
     * Main method to initialise a Duke object.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke("Duke");

        duke.start(sc);
    }
}
