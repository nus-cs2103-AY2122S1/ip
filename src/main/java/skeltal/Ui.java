package skeltal;

/**
 * A class that handles interaction with the user, Eg printing responses.
 */
public class Ui {
    /**
     * Return a boolean to the Skeltal system to introduce the chatbot to the user.
     * Prints an introduction statement.
     * @return A String containing the introduction statement.
     */
    public static String introduction() {
        String intro = "GREETINGS! You have been visited by the CS2103 skeleton who only appears once every 2147483647 years! \n" +
                        "Type /updoot in 2013 seconds or you will forever have bad bones :)";
        return intro;
    }

    /**
     * Returns a boolean to the skeltal system to signal a shutdown.
     * @return A boolean with a true value.
     */
    public static boolean bye() {
        return true;
    }

    public static String skeltalReply(String reply) {
        return Parser.parse(reply);
    }
}
