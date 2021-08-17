import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Presentation pst = new Presentation();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        pst.horizontalLine();
        System.out.println("\nHello! I'm Duke. \nWhat can I do for you?");
        pst.horizontalLine();

        Scanner sc = new Scanner(System.in);
        pst.space();
        pst.space();
        pst.enterCommand();
        String command = sc.next();
        while (!command.equals("bye")) {
            pst.response(command);
            command = sc.next();
        }

        sc.close();
        pst.space();
        System.out.println("Program exiting...");
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
