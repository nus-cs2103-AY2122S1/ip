import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scanner;
    private static ArrayList<Task> savedInputs;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        savedInputs = new ArrayList<>(100);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        chat();
    }

    /**
     * Prints (to screen) Duke's response to the user input, entered from the Command Line.
     */
    private static void chat() {
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }

            int caseId = findCase(input);

            switch(caseId) {
                case 1:
                    StringBuilder outputList = new StringBuilder();
                    outputList.append("Here are the tasks in your list:\n");
                    for (int i = 1; i <= savedInputs.size(); i++) {
                        outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
                    }
                    System.out.println(outputList.toString());
                    break;
                case 2:
                    int pos = Integer.valueOf(input.split(" ")[1]);
                    savedInputs.get(pos - 1).markAsDone();
                    break;

                case 3:
                    Todo todo = new Todo(input.substring(5));
                    System.out.println("Got it. I've added this task:\n  " + todo.toString());
                    savedInputs.add(todo);
                    System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                    break;

                case 4:
                    String by = input.split(" /by ")[1];
                    String deadlineDescription = input.split(" /by ")[0].substring(9);

                    Deadline deadline = new Deadline(deadlineDescription, by);
                    System.out.println("Got it. I've added this task:\n  " + deadline.toString());
                    savedInputs.add(deadline);
                    System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                    break;

                case 5:
                    String at = input.split(" /at ")[1];
                    String eventDescription = input.split(" /at ")[0].substring(6);

                    Event event = new Event(eventDescription, at);
                    System.out.println("Got it. I've added this task:\n  " + event.toString());
                    savedInputs.add(event);
                    System.out.println("Now you have " + savedInputs.size() + " tasks in the list.");
                    break;

                default:

            }
        }
    }

    /**
     * Returns the identifier of each case (for switch in chat method).
     *
     * @param input User entered into Command Line
     * @return caseId
     */
    private static int findCase(String input) {
        if (input.equals("list")) {
            return 1;
        } else if (input.startsWith("done ")) {
            return 2;
        } else if (input.startsWith("todo ")) {
            return 3;
        } else if (input.startsWith("deadline ")) {
            return 4;
        } else if (input.startsWith("event ")) {
            return 5;
        } else {
            return 6;
        }
    }
}
