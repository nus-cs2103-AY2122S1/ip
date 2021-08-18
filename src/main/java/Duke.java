import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private String name;
    private List<String> list;
    private String greetMsg = "What can I do for you?";
    private String exitMsg = "Bye. Hope to see you again soon!";

    /**
     * Class constructor specifying the name of the Chatbot to be created.
     *
     * @param name Name of the Chatbot.
     */
    public Duke(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    /**
     * Print the greeting message of the Chatbot.
     */
    private void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm " + this.name);
        System.out.println("    " + this.greetMsg);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print the goodbye message of the Chatbot.
     */
    private void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + this.exitMsg);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Echoes whatever is passed into <code>input</code> as argument with formatting.
     *
     * @param input The message to be printed on console.
     */
    private void echo(String input) {
        this.list.add(input);
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the formatted list of content in <code>list</code>.
     */
    private void printList() {
        int listSize = this.list.size();
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < listSize; i++) {
            int index = i + 1;
            String content = this.list.get(i);
            System.out.println("    " + index + ". " + content);
        }
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
                    this.exit();
                    return;
                case "list":
                    this.printList();
                    break;
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
