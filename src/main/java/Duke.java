import java.util.Scanner;

/**
 * Duke class for level-2 contains the main method that takes
 * in inputs.
 */
public class Duke {
    public static void main(String[] args) {
        Response response = new Response();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Scanner inputs = new Scanner(System.in);
        while (inputs.hasNext()) {
            System.out.println(response.output(inputs.nextLine()));
        }
    }
}
