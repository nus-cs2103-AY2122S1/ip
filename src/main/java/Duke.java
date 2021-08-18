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
        duchess.HandleInput(sc);
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
    public void HandleInput(Scanner sc)
    {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            PrettyPrint("I bid thee farewell.");
            return;
        }
        else if (input.equals("list"))
            PrettyPrint(dukeList.PrintList());
        else if (input.contains(" ")) {
            // Done may be called
            String[] parts = input.split(" ");
            String checkDone = parts[0];
            String index = parts[1];
            if (!checkDone.equals("done"))
                Echo(input);
            else {
                // Parsing a non-numeric string will throw a NumberFormatException
                try {
                    if (dukeList.WithinRange(Integer.parseInt(index))) {
                        // Valid done task
                        Task task = dukeList.GetTask(Integer.parseInt(index));
                        task.SetDone(true);
                        PrettyPrint("Nice! I've marked this task as done:   \n  " + task);
                    } else {
                        // "done" followed by an integer outside of range of the list
                        PrettyPrint("Apologies, that task does not exist and cannot be marked as done.");
                    }
                } catch (NumberFormatException e) {
                    // "done" followed by an invalid non-integer string input
                    PrettyPrint("The command \"done\" should be followed by an integer.");
                }
            }
        }
        else
            Echo(input);

        // Continue to read for inputs unless "bye" is called
        HandleInput(sc);
    }

    /**
     * Echoes the user's input
     * @param input the input from the user
     */
    public void Echo(String input) {
        PrettyPrint("added: " + input);
        dukeList.Add(input);
    }
}
