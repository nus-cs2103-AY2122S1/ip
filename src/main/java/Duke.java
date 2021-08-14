import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n Hello! I'm Duke\n What can I do for you?\n" + divider + '\n');
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divider + "\n " + input + "\n\n" + divider + '\n');
            input = sc.nextLine();
        }
        System.out.println(divider + "\n Bye. Hope to see you again soon!\n\n" + divider + '\n');
    }
}
