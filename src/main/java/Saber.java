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

    protected static String greeting = lineBreak + "\n      I hear your command, Master.\n" +
            "      What can I do for you?\n\n" + lineBreak;

    protected static String goodbye = lineBreak + "\n      Am I ... no longer needed, Master?\n" +
            "      I understand. I shall excuse myself.\n\n" + lineBreak;

    protected static String listMessage = "      Would you like to know what you \n" +
            "      told me to remember?\n" + "\n" +
            "      I'll list them for you, Master.\n";

    protected static Task[] taskArray = new Task[100];

    protected static int totalTask = 0;

    protected static int newTaskIndex = 0;

    enum InputCommand {
        add,
        bye,
        done,
        list,
    }

    public static void handleAddTask(String task) {
        taskArray[newTaskIndex] = new Task(task);
        System.out.println(lineBreak + "\n      I have added: "  + task + "\n\n" + lineBreak);
        totalTask++;
        newTaskIndex++;
    }

    public static void handleListTask() {
        System.out.println(lineBreak + "\n" + listMessage);
        for (int i = 0; i < totalTask; i++) {
            Task task = taskArray[i];
            System.out.println("      " + (i + 1) + ". " + task + "\n");
        }
        System.out.println(lineBreak);
    }

    public static void handleDoneTask(int taskIndex) {
        Task task = taskArray[taskIndex];
        task.markAsDone();
        System.out.println(lineBreak + "\n      Understand, Master.\n" + "      I'll mark this done right away.\n" +
                "\n        " + task + "\n\n" + lineBreak);
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + logo);
        System.out.println(greeting);
        while (!end) {
            String input = sc.nextLine();
            Command command = new Command(input);
            InputCommand commandType = command.getCommandType();
            switch (commandType) {
                case add:
                    String task = command.getArgument();
                    handleAddTask(task);
                    break;

                case bye:
                    end = true;
                    break;

                case done:
                    int taskIndex = Integer.parseInt(command.getArgument());
                    handleDoneTask(taskIndex - 1);
                    break;

                case list:
                    handleListTask();
                    break;

                default:
                    System.out.println(lineBreak + "      I'm sorry, Master. I can't fulfill your command.\n"
                            + "      What you want from me is beyond my capability\n"
                            + lineBreak);
            }
        }
        System.out.println(goodbye);
    }
}
