import java.util.Scanner;

public class Duke {
    private static String greetText = "Hello I'm Duke\nWhat can I do for you?";
    private static String exitText = "Bye. Hope to see you again soon!";
    private static String dukeLogo = " ____        _        \n"
                                   + "|  _ \\ _   _| | _____ \n"
                                   + "| | | | | | | |/ / _ \\\n"
                                   + "| |_| | |_| |   <  __/\n"
                                   + "|____/ \\__,_|_|\\_\\___|\n";


    public static void echo(String input) {
        System.out.println(input);
    }


    public static void start() {
        System.out.println(greetText);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("bye")) {
                System.out.println(exitText);
                break;
            } else {
                echo(input);
            }
        }
    }
    public static void main(String[] args) {
       Duke.start();
    }
}
