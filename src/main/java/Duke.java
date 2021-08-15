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
            } else {
                System.out.println(input);
                input = sc.nextLine();
            }
        }
        sc.close();


    }
}

// default modifier this class is only used in Duke.java
class ChatBot {

    static String line = "______________________________________\n";

    public String getStartMessage() {
        return line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line;
    }

    public String getExitMessage() {
        return line + "bye! for now...\n" + line;
    }

}
