import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] arr = new String[100];
        int count = 0;
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
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". " + arr[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                arr[count] = input;
                count++;
                String reply = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            }
        }
    }
}

