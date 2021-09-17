package duke;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Class that implements methods to present the Duke.
 */
public class Ui {

    /**
     * Constructor to create a UI object.
     */
    Ui() {
    }

    private final static String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String HORIZONTAL_DIVIDE =
        "___________________________________________________________________";

    /**
     * Prints out a welcome message.
     */
    public void welcomeUser() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can Duke do for you today?");
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out the added entry.
     *
     * @param entry THe newly-added entry.
     * @param id The #id of the newly-added entry.
     */
    public void addEntry(Entry entry, int id) {
        System.out.println("I've added entry to your list!");
        this.printEntry(entry, id);
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out the added entry.
     *
     * @param entry THe newly-added entry.
     * @param id The #id of the newly-added entry.
     */
    public String getAddEntry(Entry entry, int id) {
        return "I've added entry to your list!" +
        this.getPrintEntry(entry, id);
    }

    /**
     * Prints out a horizontal line at the end of some operation.
     */
    public void endCommand() {
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void listMatches() {
        System.out.println("Looking for matching tasks in your list..");
    }

    public String getListMatches() {
        return "Looking for matching tasks in your list..";
    }

    public void foundMatches() {
        System.out.println("Here are the matching tasks in your list..");
    }

    public String getFoundMatches() {
        return "Here are the matching tasks in your list..";
    }

    /**
     * Prints out given entry.
     *
     * @param entry Entry to be printed.
     * @param id ID of entry to be printed.
     */
    public void printEntry(Entry entry, int id) {
        System.out.println("\t" + id + "." + entry);
    }

    /**
     * Returns given entry.
     *
     * @param entry Entry to be printed.
     * @param id ID of entry to be printed.
     * @return String for given entry.
     */
    public String getPrintEntry(Entry entry, int id) {
        return "\t" + id + "." + entry;
    }

    /**
     * Prints out farewell message.
     */
    public void printGoodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out farewell message.
     *
     * @return String to say farewell to USER.
     */
    public String getGoodByeUser() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the Error Message received when Parsing.
     *
     * @param parsingError Error encountered while parsing input.
     */
    public void handleParsingError(DukeException parsingError) {
        System.out.println(parsingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out the Error Message received when Parsing.
     *
     * @param parsingError Error encountered while parsing input.
     */
    public String getParsingError(DukeException parsingError) {
        return parsingError.getMessage();
    }

    /**
     * Prints out the Error Message received in Assertions when Parsing.
     *
     * @param assertError Error encountered while parsing input.
     */
    public String getAssertingError(AssertionError assertError) {
        return assertError.getMessage();
    }

    /**
     * Returns all the commands in one large String.
     *
     * @return String containing all commands Duke uses.
     */
    public String getCommands() {
        StringBuilder helpPage = new StringBuilder("These are the available commands:\n");
        for (String command : Duke.commands) {
            helpPage.append(command).append("\n");
        }
        return helpPage.toString();
    }


    /**
     * Prints out the Error Message received when loading data from memory.
     *
     * @param loadingError Error encountered while loading data from memory.
     */
    public void handleLoadingError(DukeException loadingError) {
        System.out.println(loadingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out entry just after deletion.
     *
     * @param deletedEntry Entry that has just been deleted.
     */
    public void showDeletedEntry(Entry deletedEntry) {
        System.out.println("Removed entry\n" + deletedEntry);
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out entry just after deletion.
     *
     * @param deletedEntry Entry that has just been deleted.
     */
    public String getDeletedEntry(Entry deletedEntry) {
        return "Removed entry\n" + deletedEntry;
    }
}
