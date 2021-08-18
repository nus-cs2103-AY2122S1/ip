import java.util.Objects;
import java.util.Scanner;

/**
 * This program is a chatbot that creates a list for you.
 */
public class Duke {
    public static void main(String[] args) {
        String inData = "yo";
        String intro = "____________________________________________________________ \n"
                + "Hello! I'm Joker \n"
                + "What can I do for you? \n"
                + "____________________________________________________________";
        String[] jokerList = new String[100];
        int i = 0;

        System.out.println(intro);

        Scanner scan = new Scanner(System.in);
        while (!Objects.equals(inData, "bye")) {
            inData = scan.nextLine();
            if (Objects.equals(inData, "list")) {
                System.out.println("____________________________________________________________");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ". " + jokerList[j]);
                }
                System.out.println("____________________________________________________________");
            } else {
                jokerList[i] = inData;
                if (!Objects.equals(inData, "bye")) {
                    System.out.println("____________________________________________________________ \n"
                            + "added: "
                            + inData
                            + "\n"
                            + "____________________________________________________________");
                }
                System.out.println("array index " + i + ": " + jokerList[i]);
                i++;
            }
        }
        System.out.println("____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
