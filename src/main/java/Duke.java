import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean c = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (c) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                c = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(s);
            }
        }
    }
}
