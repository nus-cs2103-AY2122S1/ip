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
            Task toAdd;

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
                    try {
                        String reply = taskList.markDone(cmd_args);
                        System.out.println(formatReply(reply));
                    } catch (DukeException e) {
                        System.out.println(formatReply(e.toString()));
                    }
                    break;

                case ("todo"):
                    try {
                        toAdd = Todo.fromCmd(cmd_args);
                        System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    } catch (DukeException e){
                        System.out.println(formatReply(e.toString()));
                    }
                    break;

                case ("deadline"):
                    try{
                        toAdd = Deadline.fromCmd(cmd_args);
                        System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    } catch (DukeException e){
                        System.out.println(formatReply(e.toString()));
                    }
                    break;

                case ("event"):
                    try{
                        toAdd = Event.fromCmd(cmd_args);
                        System.out.println(formatReply(addTaskMessage(taskList, toAdd)));
                    } catch (DukeException e) {
                        System.out.println(formatReply(e.toString()));
                    }
                    break;

                default:
                    // unrecognised command
                    System.out.println(formatReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }
}
