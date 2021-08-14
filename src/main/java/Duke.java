import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String line = "----------------------------------------------";
        System.out.println(
                line + "\n\n" +
                "\tHello! I'm Duke\n\n" +
                "\tWhat can I do for you?\n\n" +
                line + "\n"
        );

        Scanner scanner = new Scanner(System.in);
        try {
            String text;
            boolean leave = false;
            while (!leave) {
                text = scanner.nextLine();
                if (text.equals("bye")) {
                    text = "Bye. Hope to see you again soon!";
                    leave = true;
                }
                System.out.println(
                        "\n" + line + "\n\n" +
                        "\t" + text + "\n\n" +
                        line + "\n"
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println("System.in was closed; exiting");
        }
    }
}
