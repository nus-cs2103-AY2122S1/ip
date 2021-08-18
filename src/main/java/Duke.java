import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String solidLine = "\t" + "-------------------------------------";

        System.out.println(logo + "\n" + solidLine);
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(solidLine);

        String req = "";
        while (true) {
            req = sc.nextLine();
            if (req.equals("bye")) {
                break;
            }
            System.out.println(solidLine);
            System.out.println("\t" + req);
            System.out.println(solidLine);
        }

        System.out.println(solidLine);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(solidLine);

    }
}
