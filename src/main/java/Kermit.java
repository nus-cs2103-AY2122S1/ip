import java.util.Scanner;

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

        ToDo list = new ToDo();

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete: ";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            command = sc.nextLine();
            String[] commandArr = command.split(" ");
            // Quit program
            if (command.equals("bye")) {
                System.out.println(formatText(goodbyeText));
                break;
            // List out all objects that user added to list
            } else if (command.equals("list")){
                System.out.println(formatText(list.toString()));
            // Add objects to list
            } else if (commandArr[0].equals("done")){
                int index = Integer.parseInt(commandArr[1]) - 1;
                // Get task name
                String taskText = list.completeTask(index);
                System.out.println(formatText(completeTaskText + "\n" + taskText));
            // Add new item to list
            } else {
                Task newTask = new Task(command);
                list.add(newTask);
                System.out.println(formatText("Added: " + command));
            }
        }
    }
}
