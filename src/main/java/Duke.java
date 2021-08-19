import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] arr = new Task[100];
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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ".[" + arr[i].getStatusIcon() + "] " + arr[i].getDesription());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("done ")) {
                int task = Integer.parseInt(input.split(" ")[1]) - 1;
                arr[task].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + "[" + arr[task].getStatusIcon() + "] " + arr[task].getDesription());
                System.out.println("____________________________________________________________");
            } else {
                arr[count] = new Task(input);
                count++;
                String reply = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            }
        }
    }
}

