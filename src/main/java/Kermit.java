import java.util.Scanner;

public class Kermit {
    /**
     * Adds a top and bottom horizontal line to text
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    public static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = "";

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            command = sc.nextLine();
            // Quit program
            if (command.equals("bye")) {
                System.out.println(formatText(goodbyeText));
                break;
            } else {
                System.out.println(formatText("Added: " + command));
            }
        }
    }
}
