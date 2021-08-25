package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the interactions between Duke and the Command Line Interface.
 */
public class DukeUi {

    /** Buffer containing what Duke is going to say. */
    private ArrayList<String> dukeUiBuffer;

    /** Default line separator designs. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";


    /**
     * Constructor for a new Duke UI Manager
     */
    public DukeUi() {
        dukeUiBuffer = new ArrayList<>();
    }



    public void addToDukeBuffer(String addThis) {
        dukeUiBuffer.add(addThis);
    }


    /**
     * Used to release what Duke wants to say from the buffer.
     * This would print the message between 2 line separators.
     */
    public void dukeBufferRelease() {
        System.out.println("");
        System.out.println(sepLineOpen);

        for (String dukeLine : dukeUiBuffer) {
            System.out.println(dukeLine);
        }

        System.out.println(sepLineClose);

        // Clear Duke output buffer so it can accept new lines
        this.dukeUiBuffer.clear();
    }


    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Give me something to do!");

        System.out.println(sepLine);
    }


}