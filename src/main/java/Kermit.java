import java.util.Scanner;

import java.util.ArrayList;

public class Kermit {
    /**
     * Adds a top and bottom horizontal line to text
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    private static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = "";

        String stringifiedList = "";
        // store user's objects
        ArrayList<String> list = new ArrayList<>();

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            command = sc.nextLine();
            // Quit program
            if (command.equals("bye")) {
                System.out.println(formatText(goodbyeText));
                break;
            // List out all objects that user added to list
            } else if (command.equals("list")){
                stringifiedList = "";
                for (int i = 0; i < list.size(); i++) {
                    stringifiedList += (i + 1) + ". " + list.get(i);
                    // After last item should not have line break
                    if (i < list.size() - 1) {
                        stringifiedList += "\n";
                    }
                }
                System.out.println(formatText(stringifiedList));
            // Add objects to list
            } else {
                list.add(command);
                System.out.println(formatText("Added: " + command));
            }
        }
    }
}
