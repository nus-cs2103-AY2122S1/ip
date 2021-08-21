/**
 *  This class greets the user when the bot is first used
 * @author Ryan Tian Jun
 */
public class Intro {
    private String intro = "Hello! I'm Duke\n" +
            "What can I do for you?";
    private final Logo logo = new Logo();

    public Intro() {

    }

    /**
     * Handles the intro message and displaying of logo when user
     * launches the program
     *
     * @return returns welcome message when user launches program
     */
    public String printIntro() {
        System.out.println("Hello from\n" + logo.printLogo() + "\n" + intro + "\n");
        return "Hello from\n" + logo.printLogo() + "\n" + intro + "\n";
    }
}
