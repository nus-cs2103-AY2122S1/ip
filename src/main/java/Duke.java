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

            if (userInput.equals("bye")){
                System.out.println(formatReply("BYEEEEEE!\nHope to see you again soon :)"));
                System.exit(0);
            }

            if (userInput.equals("list")) {
                System.out.println(formatReply(taskList.enumerate()));
                continue;
            }

            if (userInput.matches("(^done )[0-9]+")){
                String splitNum = userInput.split(" ")[1];
                String reply = taskList.markDone(Integer.parseInt(splitNum));
                System.out.println(formatReply(reply));
                continue;
            }

            // add tasks section
            Task toAdd = null;
            if (userInput.matches("^(todo ).+")){
                String splitName = userInput.split(" ",2)[1];
                toAdd = new Todo(splitName);
            }
            if (userInput.matches("^(deadline ).+")){
                String splitName = userInput.split(" ",2)[1];
                String[] name_by = splitName.split("/by");
                toAdd = new Deadline(name_by[0],name_by[1]);
            }
            if (userInput.matches("^(event ).+")){
                String splitName = userInput.split(" ",2)[1];
                String[] name_at = splitName.split("/at");
                toAdd = new Event(name_at[0],name_at[1]);
            }
            if (toAdd != null){
                taskList.add(toAdd);
                String reply = toAdd.addMsg();
                reply = reply + "\n" + taskList.newLength();
                System.out.println(formatReply(reply));
                continue;
            }

            // ignore invalid inputs
        }
    }
}
