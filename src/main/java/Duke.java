import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String border = "--------------------------------------------------";

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
        ArrayList<Task> taskList = new ArrayList<>();


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
                    // TODO: return list of members
                    for (Task i : taskList){
                        System.out.println(i);
                    }
                    break;
                default:
                    // TODO: add items to list
                    Task toAdd = new Task(userInput);
                    taskList.add(toAdd);
                    System.out.println(formatReply(toAdd.addMsg()));
            }
        }
    }
}
