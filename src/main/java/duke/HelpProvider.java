package duke;

/**
 * Represents the class that provide the necessary data for the help command.
 *
 * @author Sherman Ng Wei Sheng
 */
public class HelpProvider {
    /**
     * Returns the string representation of the features that duke provides.
     *
     * @return The String representation of the duke features.
     */
    public static String getContent() {
        return "Welcome to Duke! Let us go through some functionalities of Duke together!\n"
                + "\n"
                + "Here are some basic commands:\n"
                + "[help]\n"
                + "        Access this current page with explanation of the available features\n"
                + "[list]\n"
                + "        List out all the tasks that Duke is currently keeping track of\n"
                + "[bye]\n"
                + "        Exit the Duke Program\n"
                + "\n"
                + "In Duke, there are 3 types of tasks that you can add, here is how you can add them:\n"
                + "[todo <description>]\n"
                + "        Add a todo task that contains a description\n"
                + "[event <description> /at <yyyy-mm-dd>]\n"
                + "        Add an event task that contains a description with a date of the event\n"
                + "[deadline <description> /by <yyyy-mm-dd>]\n"
                + "        Add a deadline task that contains the description with a date that the deadline is due\n"
                + "\n"
                + "You can also make use of some of these cool commands to operate on the tasks:\n"
                + "[find <keyword>]\n"
                + "        Sieve out all the tasks with description containing the keyword\n"
                + "[delete <index>]\n"
                + "        Delete the task located at index (1-indexed)\n"
                + "[done <index>]\n"
                + "        Mark the task located at index as done (1-indexed)\n";
    }
}
