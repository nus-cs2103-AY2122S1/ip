package duke;

public class Ui {

    /**
     * Prints the welcome message when bot is activated
     */
    public void startBot() {
        String intro = "Hello! I'm duke.Duke\n" +
                "    What can I do for you?";
        System.out.println(intro);
    }

    /**
     * Prints the end message when exit bot
     */
    public void endBot() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
