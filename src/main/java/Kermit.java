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
        String flag = "";
        String word = "";
        
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();


        ToDo list = new ToDo();

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String listText = "Here are the tasks in your list:";
        final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete: ";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            String[] userInput = sc.nextLine().split("/");
            String commandString = userInput[0];
            String flagString = userInput.length > 1 ? userInput[1]: "";

            String[] commandArr = commandString.split(" ");
            String[] flagArr = flagString.split(" ");

            // first item is command
            command = commandArr[0];
            flag = flagArr[0];

            // Clear contents of string builders
            descriptionBuilder.setLength(0);
            flagBuilder.setLength(0);


            for (int i = 1; i < commandArr.length; i++) {
                word = commandArr[i];
                    if (i != 1) {
                        descriptionBuilder.append(" ");
                    }
                    descriptionBuilder.append(word);
            }

            for (int i = 1; i < flagArr.length; i++) {
                word = flagArr[i];
                if (i != 1) {
                    flagBuilder.append(" ");
                }
                flagBuilder.append(word);
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
                    int index = Integer.parseInt(descriptionBuilder.toString()) - 1;
                    // Get task name
                    String taskText = list.completeTask(index);
                    System.out.println(formatText(completeTaskText + "\n" + taskText));
                    break;
                // Add new todo task
                case "todo":
                    Task newToDo = new ToDos(descriptionBuilder.toString());
                    list.add(newToDo);
                    System.out.println(formatText(
                            "Got it. I've added this task:\n"
                                    + newToDo +"\nNow you have " + list.size() + " tasks in the list."));
                    break;
                // Add new deadline task
                case "deadline":
                    Task newDeadline = new Deadline(descriptionBuilder.toString(), flagBuilder.toString());
                    list.add(newDeadline);
                    System.out.println(formatText(
                            "Got it. I've added this task:\n"
                                    + newDeadline +"\nNow you have " + list.size() + " tasks in the list."));
                    break;
                default:
                    System.out.println(formatText("This is an invalid command"));
            }
        }
    }
}
