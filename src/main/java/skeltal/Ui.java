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
        String intro =
                line + "\n" +
                "           _..--\"\"---.\n" +
                        "          /          \".\n" +
                        "          `   CS2103T  l\n" +
                        "          |'._  ,._ l/\"\\\n" +
                        "          |  _J<__/.v._/\n" +
                        "           \\( ,~._,,,,-)\n" +
                        "            `-\\' \\`,,j|\n" +
                        "               \\_,____J\n" +
                        "          .--.__)--(__.--.\n" +
                        "         /  `-----..--'. j\n" +
                        "         '.- '`--` `--'  \\\\\n" +
                        "        / /  '`---'`  `-' \\\\\n" +
                        "       / /   '`----'`.-.-' \\\\\n" +
                        "     _/ /     `--- -'   \\' | \\________\n" +
                        "    |  |         ) (      `.__.---- -'\\\n" +
                        "     \\7          \\`-(               74\\\\\\\n" +
                        "     ||       _  /`-(               l|//7__\n" +
                        "     |l    ('  `-)-/_.--.          f''` -.-\"|\n" +
                        "     |\\     l\\_  `-'    .'         |     |  |\n" +
                        "     llJ   _ _)J--._.-('           |     |  l\n" +
                        "     |||( ( '_)_  .l   \". _    ..__I     |  L      -nabis\n" +
                        "GREETINGS! You have been visited by the CS2103 skeleton who only appears once every 2147483647 years! \n" +
                        "Type /updoot in 2013 seconds or you will forever have bad bones :) \n" +
                        line;
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
