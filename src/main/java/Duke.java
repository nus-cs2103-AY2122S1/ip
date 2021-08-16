import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(logo);

        String command = "";
        while (true) {
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();
            if (command.equals("bye")) {
                String exitText = "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________";
                System.out.println(exitText);
                sc.close();
                break;
            } else {
                String echoText = "____________________________________________________________\n"
                        + command + '\n'
                        + "____________________________________________________________";
                System.out.println(echoText);
            }
        }
    }
}
