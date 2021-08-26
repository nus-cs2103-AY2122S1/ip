package saber.ui;

import java.util.Scanner;

/**
 * A class to encapsulate Saber responses
 */
public class SaberUI {
    private Scanner input = new Scanner(System.in);

    protected static final String logo = "\n                            __,    _,  _ __  ______ _ __\n" +
            "              />           (      / | ( /  )(  /   ( /  )\n" +
            "             /<             `.   /--|  /--<   /--   /--<\n" +
            "            /<            (___)_/   |_/___/ (/____//   \\_\n" +
            "           /<\n" +
            " |\\_______{o}----------------------------------------------------------_\n" +
            "[\\\\\\\\\\\\\\\\\\\\\\{*}:::<=============================================-       >\n" +
            " |/~~~~~~~{o}----------------------------------------------------------~\n" +
            "           \\<\n" +
            "            \\<\n" +
            "             \\>\n";

    protected static final String lineBreak = "      |                                     |\n" +
            "{o)xxx|===============- * -===============|xxx(o}\n" +
            "      |                                     |\n";

    protected final String greeting = "      I hear your command, Master.\n" +
            "      What can I do for you?\n";

    protected final String commandNotFound = "      I'm sorry, Master.\n"
            + "      I don't ... understand your command.\n";

    protected final String genericError = "      I'm sorry, Master. I can't fulfill your command.\n"
            + "      What you want from me is beyond my capability.\n";

    protected final String storageLoadingError = "      I'm sorry, Master. I can't ...\n"
            +  "      seem to remember what\n"
            +  "      you have previously told me\n";

    protected final String storageStoringError = "      I'm really sorry, Master. I can't ...\n"
            +  "      the next time you talk to me ...\n"
            +  "      I won't be the saber.Saber that you\n"
            +  "      know...I won't remember our\n"
            +  "      interaction either. A new\n"
            +  "      saber.Saber will serve you instead.\n\n"
            +  "      Have I...done well, Master?\n";

    /**
     * A function to read input from the user
     * @return input in string
     */
    public String readInput() {
        String commandInput = input.nextLine().trim();
        return commandInput;
    }

    /**
     * Print out the Saber line break
     */
    public void showLineBreak() {
        System.out.println(lineBreak);
    }

    /**
     * Print out the greeting
     */
    public void showGreeting() {
        showLineBreak();
        System.out.println(greeting);
        showLineBreak();
    }

    /**
     * Print out the Saber logo
     */
    public void showLogo() {
        System.out.println(logo);
    }

    /**
     * Print out the command not found error when user try to give commands that are unsupported by Saber
     */
    public void showCommandNotFoundError() {
        System.out.println(commandNotFound);
    }

    /**
     * Print out the storage storing error if any error occurs during storing of tasks to the hard disk
     */
    public void showStorageStoringError() {
        showLineBreak();
        System.out.println(storageStoringError);
        showLineBreak();
    }

    /**
     * Print out the storage loading error if any error occurs during loading of tasks from the hard disk
     */
    public void showStorageLoadingError() {
        showLineBreak();
        System.out.println(storageLoadingError);
        showLineBreak();
    }

    /**
     * Print out the generic error
     */
    public void showGenericError() {
        System.out.println(genericError);
    }
}
