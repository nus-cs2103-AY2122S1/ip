import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        ChatBot bot = new ChatBot();
        System.out.println(bot.getStartMessage());

        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Boolean running = true;


        while (running) {
            if (input.equals("bye")) {
                System.out.println(bot.getExitMessage());
                running = false;
            } else if (input.equals("list")) {
                System.out.println(bot.getListMessage());
                input = sc.nextLine();
            } else {
                String message = bot.addItems(input);
                System.out.println(message);
                input = sc.nextLine();
            }
        }
        sc.close();


    }
}

// default modifier this class is only used in Duke.java
class ChatBot {

    static String line = "______________________________________\n";
    private ArrayList<String> tasks = new ArrayList<>();

    public String getStartMessage() {
        return line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line;
    }

    public String getExitMessage() {
        return line + "bye! for now...\n" + line;
    }

    public String addItems(String input) {
        tasks.add(input);
        return line + "added: " + input +"\n" + line;
    }

    public String getListMessage() {
        String listMessage = "";

        for (int i = 0; i < tasks.size(); i++) {
            listMessage = listMessage + i + ". " + tasks.get(i) + "\n";
        }
        return line + listMessage + line;
    }

}
