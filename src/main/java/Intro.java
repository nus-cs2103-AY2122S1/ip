/**
 *  This class greets the user when the bot is first used
 */
public class Intro {
    private String intro = "Hello! I'm Duke\n" +
            "What can I do for you?";
    private final Logo logo = new Logo();

    public Intro() {

    }

    public String printIntro() {
        System.out.println("Hello from\n" + logo.printLogo() + "\n" + intro);
        return "Hello from\n" + logo.printLogo() + "\n" + intro;
    }
}
