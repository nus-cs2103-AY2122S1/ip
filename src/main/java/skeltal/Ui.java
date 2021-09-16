package skeltal;

/**
 * A class that handles interaction with the user, Eg printing responses.
 */
public class Ui {
    /**
     * Return a boolean to the Skeltal system to introduce the chatbot to the user.
     * Prints an introduction statement.
     *
     * @return A String containing the introduction statement.
     */
    public static String introduction() {
        String intro = "GREETINGS! You have been visited by the CS2103 skeleton "
                + "who only appears once every 2147483647 years! \n"
                + "Type updoot in 2013 seconds or you will forever have bad bones :)";
        return intro;
    }

    /**
     * Returns a reply from skeltal using the userInput.
     *
     * @param userInput The input from the user.
     * @return A String reply from Skeltal.
     */
    public static String skeltalReply(String userInput) {
        return Parser.parse(userInput);
    }

    /**
     * Returns a reply from skeltal for updoot.
     *
     * @return A String that prints a bone
     */
    public static String updoot() {
        String bone = " _       _\n"
                + "(_'-----'_)\n"
                + "(_.'\"\"\"\"._) fsc";
        return bone;
    }
}
