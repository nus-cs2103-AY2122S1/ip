import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("------------------------\n"
                + "Hello! I'm Azure.\n"
                + "How can I help you today?\n"
                + "------------------------");

        Scanner myObj = new Scanner(System.in);

        String end = "bye";
        // String input
        while(true) {
            String input = myObj.nextLine();
            if (!input.equals(end)) {
                System.out.println("     " + input);
                continue;
            }
            System.out.println("     Bye! See you next time!");
            break;
        }
    }
}
