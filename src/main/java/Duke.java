import java.util.Scanner;

public class Duke {
    private static String welcomeText = "Hey there I'm Duke!\n"
            + "How can I help you today?\n";
    private static String byeText = "Bye! Hope to see you again!\n";

    public static void main(String[] args) {
        System.out.print(welcomeText);
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                System.out.print(byeText);
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
