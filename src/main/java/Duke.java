import java.util.Scanner;

public class Duke {
    static String border = "--------------------------------------------------";

    /**
     * format multiline data with indentations
     * @param resp String[] of each line to print
     * @return combined String for printing
     */
    private static String formatReply(String[] resp){

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

        String logo = "\n" +
                "██████╗░░█████╗░███╗░░██╗\t░█████╗░░█████╗░░█████╗░░█████╗░\n" +
                "██╔══██╗██╔══██╗████╗░██║\t██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
                "██║░░██║███████║██╔██╗██║\t╚██████║██║░░██║██║░░██║██║░░██║\n" +
                "██║░░██║██╔══██║██║╚████║\t░╚═══██║██║░░██║██║░░██║██║░░██║\n" +
                "██████╔╝██║░░██║██║░╚███║\t░█████╔╝╚█████╔╝╚█████╔╝╚█████╔╝\n" +
                "╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝\t░╚════╝░░╚════╝░░╚════╝░░╚════╝░";

        System.out.println("Loading... \n" + logo);
        System.out.println(
                "\t"+border+"\n" +
                "\tHello, I'm DAN 9000\n" +
                "\thow can I help you?\n" +
                "\t" + border);

        Scanner userScanner = new Scanner(System.in);

        while (true){
            String userInput = userScanner.nextLine();

            switch(userInput){
                case "bye":
                    System.out.println(formatReply(new String[]{"BYEEEEEE!","Hope to see you again soon :)"}));
                    System.exit(0);
                case "list":
                    System.out.println(formatReply(taskList.enumerate()));
                    break;
                default:
                    Task toAdd = new Task(userInput);
                    taskList.add(toAdd);
                    System.out.println(formatReply(toAdd.addMsg()));
            }
        }
    }
}
