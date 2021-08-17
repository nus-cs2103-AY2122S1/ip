import java.util.Scanner;
import java.lang.StringBuilder;

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
        StringBuilder strBuilder = new StringBuilder();

        ToDo list = new ToDo();

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String listText = "Here are the tasks in your list:";
        final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete: ";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            String[] commandArr = sc.nextLine().split(" ");
            // first item is command
            command = commandArr[0];

            // Clear contents of stringBuilder
            strBuilder.setLength(0);

            for (int i = 1; i < commandArr.length; i++) {
                if (i != 1) {
                    strBuilder.append(" ");
                }
                strBuilder.append(commandArr[i]);
            }

            // Quit program
            switch (command) {
                case "bye":
                    System.out.println(formatText(goodbyeText));
                    return;
                // List out all objects that user added to list
                case "list":
                    System.out.println(formatText(listText + "\n" + list));
                    break;
                // Add objects to list
                case "done":
                    int index = Integer.parseInt(strBuilder.toString()) - 1;
                    // Get task name
                    String taskText = list.completeTask(index);
                    System.out.println(formatText(completeTaskText + "\n" + taskText));
                    break;
                // Add new todo task item
                case "todo":
                    Task newTask = new ToDos(strBuilder.toString());
                    list.add(newTask);
                    System.out.println(formatText(
                            "Got it. I've added this task:\n"
                                    + newTask +"\nNow you have " + list.size() + " tasks in the list."));
                    break;
                default:
                    System.out.println(formatText("This is an invalid command"));
            }
        }
    }
}
