import java.util.*;

public class Duke {
    public static void main(String[] args) {

        // Some String format
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String start = "____________________________________________________________\n";
        String end = "____________________________________________________________";
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n" +
                    "What can I do for you?\n";
        String end_message = "Bye. I hope to talk to you again soon! :)";

        //Print welcome message to the user
        System.out.println(start + welcome + end);

        //Init new scanner to take in inputs
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        //A loop to check for bye. Else will echo the users input
        while (!input.equals("bye")) {
            System.out.println(start + input + "\n" +  end);
            input = s.nextLine();
        }

        //If the input is bye, return this message
        System.out.println(start + end_message + "\n" + end);
        s.close();
    }
}
