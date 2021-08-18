import java.util.Scanner;

/**
 * This class implements a Duke Chatbot variant: Duchess
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Duke {
    /** The horizontal bars to add style in the output.*/
    private static final String horizontalBars = "\n____________________________________________________________\n";
    /** The DukeList which holds the string stored by the user.*/
    private DukeList dukeList;

    /**
     * Constructor for Duke object
     */
    public Duke()
    {
        dukeList = new DukeList();
    }

    public static void main(String[] args) {
        Duke duchess = new Duke();
        Scanner sc = new Scanner(System.in);
        String name = "Duchess";
        PrettyPrint("Good day. I am " + name + "\nWhat can I do for you?");
        duchess.AwaitInput(sc);
    }

    /**
     * Prints to System.out fancily including horizontal bars ontop and bottom
     * @param input string to be printed fancily
     */
    public static void PrettyPrint(String input)
    {
        System.out.println(horizontalBars + input + horizontalBars);
    }

    /**
     * Gets input from user and PrettyPrints the corresponding response
     * @param sc scanner to be reused
     */
    public void AwaitInput(Scanner sc)
    {
        String output = sc.nextLine();
        switch (output) {
            case "bye":
                PrettyPrint("I bid thee farewell.");
                break;
            case "list":
                PrettyPrint(dukeList.PrintList());
                break;
            default:
                PrettyPrint("added: " + output);
                dukeList.Add(output);
                break;
        }
        if (!output.equals("bye"))
            AwaitInput(sc);
    }
}
