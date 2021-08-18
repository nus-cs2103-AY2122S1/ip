import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________ \n"
                + "Hello! I'm Joker \n"
                + "What can I do for you? \n"
                + "____________________________________________________________";
        System.out.println(intro);
        String inData = "yo";
        Scanner scan = new Scanner(System.in);
        while (!Objects.equals(inData, "bye")) {
            inData = scan.nextLine();
            if (!Objects.equals(inData, "bye")) {
                System.out.println("____________________________________________________________ \n"
                        + inData
                        + "\n"
                        + "____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
