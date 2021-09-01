public class Ui {

    private String greetingMessage = "Hola! I'm Blitz :)\nHere are the tasks in your list:";
    private String goodbyeMessage = "Adiós. Hope to see you again soon!";
    private String loadErrorMessage = "Error loading contents from file!!";

    /**
     * Returns greeting message.
     *
     * @return greeting message.
     */
    public String getGreetingMessage() {
        return greetingMessage;
    }

    /**
     * Returns goodbye message.
     *
     * @return goodbye message.
     */
    public String getGoodbyeMessage() {
        return goodbyeMessage;
    }

    /**
     * Returns loading error message.
     */
    public String showLoadingError() {
        return loadErrorMessage;
    }
}
