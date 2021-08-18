import java.util.Scanner;

public class Duke {

    private static final String PARTITION = "______________________";

    private static boolean response(String command) {
        //returns true if bot should exit, i.e. command is bye
        if (command.toLowerCase().equals("bye")) {
            System.out.println(PARTITION
                    + "\n Bye. Hope to see you again soon! \n"
                    + PARTITION);
            return true;
        } else {
            System.out.println(PARTITION + "\n"
                    + command + "\n" + PARTITION);
            return false;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(PARTITION
                + "\n Hello! I'm Duke"
                + "\n What can I do for you? \n" + PARTITION);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (response(sc.next())) break;
        }
        sc.close();

    }
}
