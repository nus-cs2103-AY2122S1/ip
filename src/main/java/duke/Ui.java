package duke;

/**
 * This represents the user interface that interacts with the user.
 */
public class Ui {


    /**
     * This method outputs a greeting message that is shown to the user.
     */
    public String showWelcome() {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        return welcomeMessage;
    }

    /**
     * This method outputs a given String text that is bordered above and below by lines.
     * @param text String that gets bordered.
     */
    public void stringWithDivider(String text) {
        System.out.println("_______________________________________________________________");
        System.out.println(text);
        System.out.println("_______________________________________________________________");
    }
}
