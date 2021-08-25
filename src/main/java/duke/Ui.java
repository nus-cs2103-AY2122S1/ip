package duke;

import java.util.Scanner;

public class Ui {
    private final static String LOGO =
            "                 |`._         |\\\n" +
                    "                 `   `.  .    | `.    |`.\n" +
                    "                  .    `.|`-. |   `-..'  \\           _,.-'\n" +
                    "                  '      `-. `.           \\ /|   _,-'   /\n" +
                    "              .--..'        `._`           ` |.-'      /\n" +
                    "               \\   |                                  /\n" +
                    "            ,..'   '                                 /\n" +
                    "            `.                                      /\n" +
                    "            _`.---                                 /\n" +
                    "        _,-'               `.                 ,-  /\"-._\n" +
                    "      ,\"                   | `.             ,'|   `    `.\n" +
                    "    .'                     |   `.         .'  |    .     `.\n" +
                    "  ,'                       '   ()`.     ,'()  '    |       `.\n" +
                    "'-.                    |`.  `.....-'    -----' _   |         .\n" +
                    " / ,   ________..'     '  `-._              _.'/   |         :\n" +
                    " ` '-\"\" _,.--\"'         \\   | `\"+--......-+' //   j `\"--.. , '\n" +
                    "    `.'\"    .'           `. |   |     |   / //    .       ` '\n" +
                    "      `.   /               `'   |    j   /,.'     '\n" +
                    "        \\ /                  `-.|_   |_.-'       /\\\n" +
                    "         /                        `\"\"          .'  \\\n" +
                    "        j                                           .\n" +
                    "        |                                 _,        |\n" +
                    "        |             ,^._            _.-\"          '\n" +
                    "        |          _.'    `'\"\"`----`\"'   `._       '\n" +
                    "        j__     _,'                         `-.'-.\"`\n" +
                    "          ',-.,' mh";
    private final static String BOOT_MESSAGE = "Whats'up ma dawg?";
    private final static String LOAD_MESSAGE = "Finding previous save file...";
    private final static String BEGIN_MESSAGE = "What can I do for you ma dawg?";
    private final static String BYE_MESSAGE = "Cya next time ma dawg";

    private static final Scanner sc = new Scanner(System.in);

    public static void display(String content) {
        System.out.println(
                "69696969696969696969696969696969696969696969696969696969696966969696969696969696969696969696969\n"
                        + content
                        + "\n69696969696969696969696969696969696969696969696969696969696966969696969696969696969696969696969\n"
        );
    }
    public static void greet() {
        display(LOGO);
        display(BOOT_MESSAGE);
        display(LOAD_MESSAGE);
    }

    public static void begin() {
        display(BEGIN_MESSAGE);
    }

    public static void bye() {
        display(BYE_MESSAGE);
    }

    public static String readLine() {
        return sc.nextLine();
    }
}
