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
     * Constructor for a new Duke UI Manager
     */
    public DukeUi() {
        dukeUiBuffer = new ArrayList<>();
    }


    /**
     * Adds a string into Duke's UI buffer for printing later.
     * @param addThis the String to add into the buffer.
     */
    public void addToDukeBuffer(String addThis) {
        dukeUiBuffer.add(addThis);
    }


    /**
     * Used to release what Duke wants to say from the buffer.
     * This would print the message between 2 line separators.
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

        String logo = "The DUKE SEAGULL";
        returnThis = "Hello from\n" + logo;
        returnThis = returnThis + "\nGive me something to do!";

        System.out.println(returnThis);
        System.out.println(sepLine);

        return returnThis;
    }


}
