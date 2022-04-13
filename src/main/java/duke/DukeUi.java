package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the interactions between Duke and the Command Line Interface.
 */
public class DukeUi {

    /** Default line separator designs. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";

    /** Buffer containing what Duke is going to say. */
    private ArrayList<String> dukeUiBuffer;

    /**
     * Constructs a new Duke UI Manager.
     */
    public DukeUi() {
        dukeUiBuffer = new ArrayList<>();
    }


    /**
     * Adds a string into Duke's UI buffer for printing later.
     *
     * @param addThis the String to add into the buffer.
     */
    public void addToDukeBuffer(String addThis) {
        assert dukeUiBuffer != null : "Duke UI Buffer is missing!!";
        dukeUiBuffer.add(addThis);
    }


    /**
     * Releases what Duke wants to say from the buffer. Prints the message between 2 line separators.
     *
     * @return the String containing what Duke says at one go.
     */
    public String dukeBufferRelease() {
        // GUI Output
        String returnThis = "";

        for (String dukeLine : dukeUiBuffer) {
            returnThis = returnThis + dukeLine + "\n";
        }

        // Text Output
        System.out.println("");
        System.out.println(sepLineOpen);
        System.out.println(returnThis);
        System.out.println(sepLineClose);

        // Clear Duke output buffer so it can accept new lines
        this.dukeUiBuffer.clear();

        return returnThis;
    }


    /**
     * Prints and returns the welcome message.
     *
     * @return the Welcome Message.
     */
    public String printWelcomeMessage() {
        String returnThis;

        String logo = "The SEAGULL TASKER";
        returnThis = "Hello from\n" + logo;
        returnThis = returnThis + "\nGive me something to do!";

        System.out.println(returnThis);
        System.out.println(sepLine);

        return returnThis;
    }

    /**
     * Adds the help screen for Duke into the UI buffer.
     */
    public void showHelpScreen() {
        addToDukeBuffer("Welcome to Seagull Help!!!");
        addToDukeBuffer("");
        addToDukeBuffer("Command Summary:");
        addToDukeBuffer("");
        addToDukeBuffer("");
        addToDukeBuffer("Adding Tasks:");
        addToDukeBuffer("");
        addToDukeBuffer("todo <taskName>  >> Adds a todo task.");
        addToDukeBuffer("");
        addToDukeBuffer("deadline <taskName> /by <date> ");
        addToDukeBuffer("    >> Adds a Deadline Task.");
        addToDukeBuffer("    Date must be in format: 'D/M/YY', 'DD/MM/YYYY' or 'DD Month YYYY'.");
        addToDukeBuffer("");
        addToDukeBuffer("event <taskName> /at <eventLocation>.");
        addToDukeBuffer("");
        addToDukeBuffer("");
        addToDukeBuffer("Task Processing:");
        addToDukeBuffer("list  >> Lists out current task list.");
        addToDukeBuffer("");
        addToDukeBuffer("done <taskNo>  >> Sets indicated task to 'Done'.");
        addToDukeBuffer("");
        addToDukeBuffer("delete <taskNo> >> Deletes the indicated task.");
        addToDukeBuffer("");
        addToDukeBuffer("find <thing> >> Searches task list for thing.");
        addToDukeBuffer("");
        addToDukeBuffer("");
        addToDukeBuffer("bye    >> Closes me :(");
        addToDukeBuffer("help OR help <anything>  >> Shows this help screen.");
    }

}
