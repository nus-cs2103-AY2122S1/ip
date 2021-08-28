package duke;

import java.util.Scanner;

/**
 * Class to handle user interaction.
 */
public class Ui {
    private static final String LOGO =
            "                 |`._         |\\\n"
                    + "                 `   `.  .    | `.    |`.\n"
                    + "                  .    `.|`-. |   `-..'  \\           _,.-'\n"
                    + "                  '      `-. `.           \\ /|   _,-'   /\n"
                    + "              .--..'        `._`           ` |.-'      /\n"
                    + "               \\   |                                  /\n"
                    + "            ,..'   '                                 /\n"
                    + "            `.                                      /\n"
                    + "            _`.---                                 /\n"
                    + "        _,-'               `.                 ,-  /\"-._\n"
                    + "      ,\"                   | `.             ,'|   `    `.\n"
                    + "    .'                     |   `.         .'  |    .     `.\n"
                    + "  ,'                       '   ()`.     ,'()  '    |       `.\n"
                    + "'-.                    |`.  `.....-'    -----' _   |         .\n"
                    + " / ,   ________..'     '  `-._              _.'/   |         :\n"
                    + " ` '-\"\" _,.--\"'         \\   | `\"+--......-+' //   j `\"--.. , '\n"
                    + "    `.'\"    .'           `. |   |     |   / //    .       ` '\n"
                    + "      `.   /               `'   |    j   /,.'     '\n"
                    + "        \\ /                  `-.|_   |_.-'       /\\\n"
                    + "         /                        `\"\"          .'  \\\n"
                    + "        j                                           .\n"
                    + "        |                                 _,        |\n"
                    + "        |             ,^._            _.-\"          '\n"
                    + "        |          _.'    `'\"\"`----`\"'   `._       '\n"
                    + "        j__     _,'                         `-.'-.\"`\n"
                    + "          ',-.,' mh";
    private static final String BOOT_MESSAGE = "Whats'up ma dawg?";
    private static final String LOAD_MESSAGE = "Finding previous save file...";
    private static final String BEGIN_MESSAGE = "What can I do for you ma dawg?";
    private static final String BYE_MESSAGE = "Cya next time ma dawg";

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints out content with borders.
     *
     * @param content Content of message.
     */
    public static void display(String content) {
        System.out.println(
                "69696969696969696969696969696969696969696969696969696969696966969696969696969696969696969696969\n"
                        + content
                        + "\n696969696969696969696969696969696969696969696969"
                        + "69696969669696969696969696969696969696969696969\n"
        );
    }

    /**
     * Displays message when starting bot.
     */
    public static void greet() {
        display(LOGO);
        display(BOOT_MESSAGE);
        display(LOAD_MESSAGE);
    }

    /**
     * Displays message when save file loaded.
     */
    public static void begin() {
        display(BEGIN_MESSAGE);
    }

    /**
     * Displays message when exiting bot.
     */
    public static void bye() {
        display(BYE_MESSAGE);
    }

    /**
     * Reads user input.
     *
     * @return User input.
     */
    public static String readLine() {
        return sc.nextLine();
    }
}
