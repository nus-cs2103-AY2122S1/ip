import java.util.Scanner;

public class Saber {
    protected static String logo = "                            __,    _,  _ __  ______ _ __\n" +
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

    protected static String lineBreak = "      |                                     |\n" +
            "{o)xxx|===============- * -===============|xxx(o}\n" +
            "      |                                     |\n";

    protected static String greeting = lineBreak + "      I hear your command, Master.\n" +
            "      What can I do for you?\n" + lineBreak;

    protected static String goodbye = lineBreak + "      Am I ... no longer needed, Master?\n" +
            "      I understand. I shall excuse myself.\n" + lineBreak;

    enum Command {
        bye,
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + logo);
        System.out.println(greeting);
        while (!end) {
            String inputCommand = sc.nextLine();
            if (inputCommand.equals(Command.bye.name())) {
                end = true;
            } else {
                System.out.println(lineBreak + "      "  + inputCommand + "\n" + lineBreak);
            }
        }
        System.out.println(goodbye);
    }
}
