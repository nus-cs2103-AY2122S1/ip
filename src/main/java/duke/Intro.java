package duke;

/**
 *  This class greets the user when the bot is first used.
 *
 * @author Ryan Tian Jun.
 */
public class Intro {
    private String introMessage = "Hello! I'm Duke!\n"
            + "What can I do for you? Type something to get started!";
    private final Logo logo = new Logo();

    public Intro() {

    }

    /**
     * Handles the intro message and displaying of logo when user
     * launches the program.
     *
     * @return returns welcome message when user launches program.
     */
    public String printIntro() {
        return "Hello from\n" + logo + "\n" + introMessage + "\n";
    }
}
