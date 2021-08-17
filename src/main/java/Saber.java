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

    protected static String listMessage = "      Would you like to know what you \n" +
            "      told me to remember?\n" + "\n" +
            "      I'll list them for you, Master.\n";

    protected static String[] taskArray = new String[100];

    enum Command {
        bye,
        list,
    }

    public static void main(String[] args) {
        boolean end = false;
        int totalTask = 0;
        int newTaskIndex = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + logo);
        System.out.println(greeting);
        while (!end) {
            String inputCommand = sc.nextLine();
            if (inputCommand.equals(Command.bye.name())) {
                end = true;
            } else if (inputCommand.equals(Command.list.name())) {
                System.out.println(lineBreak);
                System.out.println(listMessage);
                for (int i = 0; i < totalTask; i++) {
                    String task = taskArray[i];
                    System.out.println("      " + (i + 1) + ". " + task + "\n");
                }
                System.out.println(lineBreak);
            } else {
                taskArray[newTaskIndex] = inputCommand;
                System.out.println(lineBreak + "      I have added: "  + inputCommand + "\n" + lineBreak);
                totalTask++;
                newTaskIndex++;
            }
        }
        System.out.println(goodbye);
    }
}
