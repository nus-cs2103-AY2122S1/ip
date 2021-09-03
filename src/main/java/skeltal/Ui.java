package skeltal;

/**
 * A class that handles interaction with the user, Eg printing responses.
 */
public class Ui {

    private static String line = "---------------------------------------------";

    /**
     * Prints an introduction statement.
     */
    public static void introduction() {
        System.out.println(line);
        System.out.println(
                "           _..--\"\"---.\n" +
                "          /           \".\n" +
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
                "     |||( ( '_)_  .l   \". _    ..__I     |  L\n" +
                "     ^\\\\\\||`'   \"   '\"-. \" )''`'---'     L.-'`-.._\n" +
                "          \\ |           ) /-              ``'`-.._``-.\n" +
                "          l l          / / |                      |''|\n" +
                "           \" \\        / /   \"-..__                |  |\n" +
                "           | |       / /          1       ,- t-...J_.'\n" +
                "           | |      / /           |       |  |\n" +
                "           J  \\  /\"  (            l       |  |\n" +
                "           | ().'`-()/            |       |  |\n" +
                "          _.-\"_.____/             l       l.-l\n" +
                "      _.-\"_.+\"|                  /        \\.  \\\n" +
                "/\"\\.-\"_.-\"  | |                 /          \\   \\\n" +
                "\\_   \"      | |                1   -nabis   | `'|\n" +
                "  |ll       | |                |            i   |\n" +
                "  \\\\\\       |-\\               \\j ..          L,,'. `/\n" +
                " __\\\\\\     ( .-\\           .--'    ``--../..'      '-..\n" +
                "   `'''`----`\\\\\\\\ .....--'''\n" +
                "              \\\\\\\\                                   ''\n" +
                "GREETINGS! You have been visited by the CS2103 skeleton who only appears once every 2147483647 years! \n" +
                "Type /updoot in 2013 seconds or you will forever have bad bones :)");
        System.out.println(line);
    }

    /**
     * Returns a boolean to the skeltal system to signal a shutdown.
     * Prints a goodbye statement to the user.
     * @return A boolean with a true value.
     */
    public static boolean bye() {
        System.out.println(Ui.line);
        System.out.println("Thanks for chatting! Hope to see you again soon! ");
        System.out.println(Ui.line);
        return true;
    }

    /**
     * Returns a line that is used to enclose Skeltal's responses.
     * @return A String of a line.
     */
    public static String line() {
        return Ui.line;
    }
}
