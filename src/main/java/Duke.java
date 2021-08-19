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

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String[] tasks = new String[100];

        System.out.println(greetingMessage);

        String input = scanner.next();
        int numOfTasks = 1;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 1; i < numOfTasks; i++) {
                    System.out.println("\t" + i + ". " + tasks[i - 1]);
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine + "\n\tadded: " + input + "\n" + horizontalLine);
                tasks[numOfTasks - 1] = input;
                numOfTasks++;
            }
            input = scanner.next();
        }

        System.out.println(byeMessage);
    }
}
