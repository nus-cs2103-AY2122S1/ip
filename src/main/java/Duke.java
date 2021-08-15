import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner textInput = new Scanner(System.in);

        while (true) {
            String input = textInput.nextLine();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                break;
            }
        }
        System.out.println("Bye bye. Duke going to sleep now.");
    }
}
