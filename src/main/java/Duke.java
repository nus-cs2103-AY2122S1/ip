import java.util.*;

public class Duke {
    private static final String HELLO = "\nHello! I'm Duke \nWhat can I do for you?\n";
    private static final String LINE = "===============================================";
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BYE = "\nBye. Hope to see you again soon!\n";


    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.start();
    }

    public void start() {
        System.out.println(logo + HELLO + LINE);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(LINE + "\n" + userInput + "\n" + LINE);
            userInput = sc.nextLine();
        }
        System.out.println(LINE + BYE + LINE);
        sc.close();
    }
}

