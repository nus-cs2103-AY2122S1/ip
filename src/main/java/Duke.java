import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startBot();

        //level 1
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(message);
            }
        }
        sc.close();
    }

    public static void startBot() {
        String intro = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(intro);
    }
}
