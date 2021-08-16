import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String divider = "---------------------------";

        Scanner in = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke" +
                        "\n" +
                        "What can I do for you?" +
                        "\n" +
                        divider);

        while (true) {
            String echo = in.nextLine();  // Read user input

            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(divider + "\n" + echo + "\n" + "\n" + divider);
        }
    }
}
