import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "\t____________________________________________________________";
        String greetingMessage = horizontalLine
                + "\n\tHello from \n"
                + logo
                + "\tHow can I help you?\n"
                + horizontalLine;
        String byeMessage = horizontalLine
                + "\n\tBye! Thank you for chatting with me!\n"
                + horizontalLine;

        Scanner scanner = new Scanner(System.in);

        System.out.println(greetingMessage);

        String input = scanner.next();

        while (!input.equals("bye")) {
            System.out.println(horizontalLine + "\n\t" + input + "\n" + horizontalLine);
            input = scanner.next();
        }

        System.out.println(byeMessage);
    }
}
