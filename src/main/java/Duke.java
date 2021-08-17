import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String linebreak = "~~~~~~~~~~";
        String command;

        // This string was the initial string
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot
        System.out.println("Hi! I'm Taro, short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println("What can I do for you today? :>");
        System.out.println("(Tip: Type \"bye\" to leave!)");
        System.out.println(linebreak);


        // This part listens for user input and repeats
        while (true) {
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye bye!! Thanks for stopping by!");
                System.out.println(linebreak);
                break;
            }

            System.out.println(command);
            System.out.println(linebreak);
        }
    }
}
