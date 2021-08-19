import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String startUp = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(line + startUp + line);

        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(line + " " + input + System.lineSeparator() + line);

        }
    }
}
