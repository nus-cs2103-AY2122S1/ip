import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Print welcome text
        String line = "--------------------------------\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + logo + "\nHello! I'm Duke :)\nWhat can I do for you?\n" + line);

        // Echos input back to user. Program exits when user inputs "bye"
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                System.exit(0);
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
    }
}
