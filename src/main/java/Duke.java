import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        boolean end = false;
        String endCmd = "bye";
        Scanner sc = new Scanner(System.in);

        while (!end) {
            String cmd = sc.nextLine();
            System.out.println(separator);

            if (cmd.equals(endCmd)) {
                System.out.println("Bye bye! See you again soon!");
                end = true;
            } else {
                System.out.println(cmd);
            }

            System.out.println(separator);
        }
    }
}
