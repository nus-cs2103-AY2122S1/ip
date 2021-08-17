import java.util.Scanner;

public class Duke {
    static String border = "--------------------------------------------------";

    /**
     * format multiline data with indentations
     * @param rawStr String[] of each line to print
     * @return combined String for printing
     */
    private static String formatReply(String rawStr){
        String[] resp = rawStr.split("\n");
        String textOut = "";
        for (String line : resp){
            textOut += "\t" + line + "\n";
        }
        return "\t" + border + "\n"
                + textOut
                + "\t" + border;
    }

    private static String addTaskMessage(TaskArrayList taskList, Task toAdd){
        taskList.add(toAdd);
        String reply = toAdd.addMsg();
        return reply + "\n" + taskList.newLength();
    }

    public static void main(String[] args) {
        TaskArrayList taskList = new TaskArrayList();

        String logo =
                "  _____          _   _    ___   ___   ___   ___  \n" +
                " |  __ \\   /\\   | \\ | |  / _ \\ / _ \\ / _ \\ / _ \\ \n" +
                " | |  | | /  \\  |  \\| | | (_) | | | | | | | | | |\n" +
                " | |  | |/ /\\ \\ | . ` |  \\__, | | | | | | | | | |\n" +
                " | |__| / ____ \\| |\\  |    / /| |_| | |_| | |_| |\n" +
                " |_____/_/    \\_\\_| \\_|   /_/  \\___/ \\___/ \\___/ \n";

        System.out.println("Loading... \n" + logo);
        System.out.println(
                "\t"+border+"\n" +
                "\tHello, I'm DAN 9000\n" +
                "\tHow can I help you?\n" +
                "\t" + border);

        Scanner userScanner = new Scanner(System.in);

        while (true){
            String userInput = userScanner.nextLine();
            // cmd_args splits the command from arguments
            // if no arguments, length will be 1
            // if arguments, length will be 2
            String[] cmd_args = userInput.split(" ",2);
            Task toAdd = null;

            // switch case to split by command
            switch (cmd_args[0]) {
                case ("bye"):
                    if (cmd_args.length > 1){
                        System.out.println(formatReply("☹ OOPS!!! bye can't take arguments."));
                        break;
                    }
                    System.out.println(formatReply("BYEEEEEE!\nHope to see you again soon :)"));
                    System.exit(0);
                    break;

                case ("list"):
                    if (cmd_args.length > 1){
                        System.out.println(formatReply("☹ OOPS!!! list can't take arguments."));
                        break;
                    }
                    System.out.println(formatReply(taskList.enumerate()));
                    break;

                case ("done"):
                    if (cmd_args.length != 2){
                        System.out.println(formatReply("☹ OOPS!!! done takes exactly one argument."));
                        break;
                    }
                    if (!cmd_args[1].matches("[0-9]+")){
                        System.out.println(formatReply("☹ OOPS!!! argument for done must be exactly one integer."));
                        break;
                    }
                    String reply = taskList.markDone(Integer.parseInt(cmd_args[1]));
                    System.out.println(formatReply(reply));
                    break;

                case ("todo"):
                    toAdd = new Todo(cmd_args[1]);
                    System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    break;

                case ("deadline"):
                    String[] name_by = cmd_args[1].split("/by");
                    toAdd = new Deadline(name_by[0],name_by[1]);
                    System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    break;

                case ("event"):
                    String[] name_at = cmd_args[1].split("/at");
                    toAdd = new Event(name_at[0],name_at[1]);
                    System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    break;

                default:
                    // unrecognised command
                    System.out.println(formatReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }
}
