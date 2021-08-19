import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String lineBreak = "\t____________________________________________________________";
        System.out.println(lineBreak
                + "\n\tHello! I'm Luke, your slightly useful personal assistant!\n"
                + "\tWhat can I do for you, my liege?\n"
                + "\ttype 'bye' to end\n"
                + lineBreak);

        String response = scanner.nextLine();

        while (!response.equals("bye")) {
            System.out.println(lineBreak
                    + "\n\t"
                    + response
                    + "\n"
                    + lineBreak);
            response = scanner.nextLine();
        }

        System.out.println(lineBreak
                + "\n\tBye! Talk again sometime!\n"
                + lineBreak);

        scanner.close();
    }
}
