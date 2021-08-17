import java.util.Scanner;
import java.lang.StringBuilder;

public class Kermit {
    /**
     * Adds a top and bottom horizontal line to text
     *
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    private static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }

    /**
     * Pretty format text when task is added
     *
     * @param task Task that is added to list
     * @param list List that task was added to
     * @return String stating task was successfully added
     */
    private static String printAddTask(Task task, ToDo list) {
        return "Got it. I've added this task:\n"
                + task +"\nNow you have " + list.size() + " tasks in the list.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = "";
        String flag;
        String word;
        
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();


        ToDo list = new ToDo();

        final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
        final String listText = "Here are the tasks in your list:";
        final String invalidCommandText = "☹ BURP-ribbit ribbit. I'm sorry, but I don't know what that means :-(";
        final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete:";
        final String goodbyeText = "Bye. Hope to see you again soon!";

        System.out.println(formatText(introductionText));

        while (true) {
            try {
                // Task description and flag should be separated by some /command
                String[] userInput = sc.nextLine().split("/");
                String commandString = userInput[0];
                String flagString = userInput.length > 1 ? userInput[1] : "";

                String[] commandArr = commandString.split(" ");
                String[] flagArr = flagString.split(" ");

                // first item is command
                command = commandArr[0];
                flag = flagArr[0];

                String description = "";
                String flagArguments = "";

                // Clear contents of string builders
                descriptionBuilder.setLength(0);
                flagBuilder.setLength(0);

                // Get description of task
                for (int i = 1; i < commandArr.length; i++) {
                    word = commandArr[i];
                    if (i != 1) {
                        descriptionBuilder.append(" ");
                    }
                    descriptionBuilder.append(word);
                }

                // Get the flags provided for task
                for (int i = 1; i < flagArr.length; i++) {
                    word = flagArr[i];
                    if (i != 1) {
                        flagBuilder.append(" ");
                    }
                    flagBuilder.append(word);
                }

                description = descriptionBuilder.toString();
                flagArguments = flagBuilder.toString();

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
                        int index = Integer.parseInt(description) - 1;
                        // Get task name
                        String taskText = list.completeTask(index);
                        System.out.println(formatText(completeTaskText + "\n" + taskText));
                        break;
                    // Add new todo task
                    case "todo":
                        if (description.equals("")) {
                            throw new KermitException("The description of a " + command + " cannot be empty");
                        }

                        Task newToDo = new ToDos(description);
                        list.add(newToDo);
                        System.out.println(formatText(printAddTask(newToDo, list)));
                        break;
                    // Add new deadline task
                    case "deadline":
                        if (description.equals("")) {
                            throw new KermitException("The description of a " + command + " cannot be empty");
                        }

                        // Empty flag arguments for tasks error
                        if (flagArguments.equals("")) {
                            throw new KermitException("Deadlines should be formatted as\n deadline <description> /by <deadline>");
                        }
                        Task newDeadline = new Deadline(description, flagArguments);
                        list.add(newDeadline);
                        System.out.println(formatText(printAddTask(newDeadline, list)));
                        break;

                    // Add new event task
                    case "event":
                        if (description.equals("")) {
                            throw new KermitException("The description of a " + command + " cannot be empty");
                        }
                        // Empty flag arguments for tasks error
                        if (flagArguments.equals("")) {
                            throw new KermitException("Events should be formatted as\n event <description> /at <deadline>");
                        }
                        Task newEvent = new Event(description, flagArguments);
                        list.add(newEvent);
                        System.out.println(formatText(printAddTask(newEvent, list)));
                        break;
                    default:
                        throw new KermitException(invalidCommandText);
                }
            } catch (KermitException e) {
                System.out.println(formatText(e.getMessage()));
            }
        }
    }
}
