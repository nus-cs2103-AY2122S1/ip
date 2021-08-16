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
            if (userInput.equals("bye")) {
                System.out.println(formatReply(new String[]{"BYEEEEEE!","Hope to see you again soon :)"}));
                System.exit(0);
            }

        }
    }
}
