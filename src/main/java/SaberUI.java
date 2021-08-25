import java.util.Scanner;

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
            +  "      I won't be the Saber that you\n"
            +  "      know...I won't remember our\n"
            +  "      interaction either. A new\n"
            +  "      Saber will serve you instead.\n\n"
            +  "      Have I...done well, Master?\n";

    public String readInput() {
        String commandInput = input.nextLine().trim();
        return commandInput;
    }

    public void showLineBreak() {
        System.out.println(lineBreak);
    }

    public void showGreeting() {
        showLineBreak();
        System.out.println(greeting);
        showLineBreak();
    }

    public void showLogo() {
        System.out.println(logo);
    }

    public void showCommandNotFoundError() {
        System.out.println(commandNotFound);
    }

    public void showStorageStoringError() {
        showLineBreak();
        System.out.println(storageStoringError);
        showLineBreak();
    }

    public void showStorageLoadingError() {
        showLineBreak();
        System.out.println(storageLoadingError);
        showLineBreak();
    }

    public void showGenericError() {
        System.out.println(genericError);
    }
}
