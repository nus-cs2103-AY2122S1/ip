import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String mode = "greeting";
        String horizontalLine = "____________________________________________________________";
        String greeting = horizontalLine
                + "\nHello! I'm Duke"
                + "\n What can I do for you?"
                + "\n" + horizontalLine;
        Scanner commandScanner = new Scanner(System.in);
        System.out.println(greeting);
        while (!mode.equals("bye")) {
            String userNextCommand = commandScanner.nextLine();
            if (userNextCommand.equals("bye")) {

                mode = "bye";
                String byeString = horizontalLine
                        + "\nBye. Hope to see you again soon!\n"
                        + horizontalLine;
                System.out.println(byeString);

            } else {

                mode = "echo";
                String echoString = horizontalLine
                        + "\n"
                        + userNextCommand
                        + "\n"
                        + horizontalLine;
                System.out.println(echoString);

            }

        }
    }
}
