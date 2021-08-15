/**
 * Encapsulates the ChatBot function and supports echoing of commands.
 *
 * @author Clifford
 */

public class ChatBot {
    private boolean isRunning;

    public ChatBot() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * greet is called when the user starts up the program.
     *
     * @return a String when user starts interacting with ChatBot
     */
    public String greet() {
        return "Hello! I'm Chatty Clifford! \nHow may I be of service to you?";
    }

    /**
     * farewell is called when the user exits the program.
     *
     * @return a String when user finishes interacting with ChatBot
     */
    public String farewell() {
        this.isRunning = false;
        return "Bye! See you next time!";
    }

    /**
     * listen decides what the ChatBot should do depending on the user input
     *
     * @param input the request by the user
     * @return the response by the ChatBot
     */
    public String listen(String input) {
        if(input.toLowerCase().trim().equals("bye")) {
            return farewell();
        }
        return echo(input);
    }

    /**
     * echo respond to the user by returning what the user inputs.
     *
     * @param input the request by the user
     * @return a response exactly said by the user
     */
    public String echo(String input) {
        return input;
    }
}
