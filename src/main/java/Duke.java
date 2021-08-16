import java.util.ArrayList;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        initialiseBot();

        boolean status = true;

        Scanner sc = new Scanner(System.in);

        while (status) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                status = false;
                closeBot();
            } else {
                echo(message);
            }
        }

        sc.close();

    }

    private static void closeBot() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void initialiseBot() {
        String message = "Hello! I'm Duke \n"
                + "What can I do for you?";
        System.out.println(message);
    }


}
