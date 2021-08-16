import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        final String INTRO = "Hello! I'm Duke\n" +
                "What can I do for you?";

        final String OUTRO = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);

        String command;
        String listCommand = "";
        int i = 1;

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println(INTRO);
        while (true) {
            command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println(OUTRO);
                break;

            } else if (command.equals("list")) {
                System.out.println(listCommand);

            } else {
                listCommand = listCommand + i + ". " + command + "\n";
                i++;
                System.out.println("added: " + command);
            }
        }
    }
}
