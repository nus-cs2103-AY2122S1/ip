import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                String bye = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(bye);
                return;
            }
            String reply = "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________";
            System.out.println(reply);
        }
    }
}

