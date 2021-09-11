package neo;

/**
 *  This class greets the user when the bot is first used.
 *
 * @author Ryan Tian Jun.
 */
public class Intro {
    private String introMessage = "Hello! I'm Neo!\n"
            + "What can I do for you? Type something to get started!";

    public Intro() {

    }

    /**
     * Handles the intro message and displaying of logo when user
     * launches the program.
     *
     * @return returns welcome message when user launches program.
     */
    public String printIntro() {
        return introMessage + "\n";
    }
}
