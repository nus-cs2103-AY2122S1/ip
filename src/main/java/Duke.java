import java.util.Scanner;
/**
 * Duke class for level-2 contains the main method that takes
 * in inputs.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Scanner inputs = new Scanner(System.in);
        while (inputs.hasNext()) {
            Response response = new Response(inputs.next());
            System.out.println(response.Echo());
        }
    }
}
