import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String welcomeString = "____________________________________________________________\n"
                + "Yo! Duke here.\n"
                + "What do ya want from me?\n"
                + "____________________________________________________________\n";
        String byeString = "____________________________________________________________\n"
                + "Bye bye! Wake me up when ya need me again:)\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeString);

        // ask for user input
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        while (!nextLine.equals("bye")) {
            String nextToPrint = "____________________________________________________________\n"
                    + nextLine;
            System.out.println(nextToPrint);
            System.out.println("____________________________________________________________\n");
            nextLine = in.nextLine();
        }

        System.out.println(byeString);
        in.close();
    }
}
