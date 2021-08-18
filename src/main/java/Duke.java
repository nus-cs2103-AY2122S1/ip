import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?");

        Scanner newScan = new Scanner(System.in);
        ArrayList<Task> contents = new ArrayList<>();
        String userInput = newScan.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                if (contents.size() == 0) {
                    System.out.println("    ***\n" + "    There is currently nothing in the list. \n" +
                            "    ***\n");
                } else {
                    int counter = 1;
                    System.out.println("    ***\n" + "    These are your tasks in the list:");
                    for (Task x: contents) {
                        System.out.println("      " + counter + ". " + x.getStatusIcon() + " " +
                                x.getDescription());
                        counter++;
                    }
                    System.out.println("    ***\n");
                }
            } else if (userInput.startsWith("done") || userInput.startsWith("delete")) {
                try {
                    if (userInput.startsWith("done")) {
                        String index = userInput.substring(5);
                        int x = Integer.parseInt(index);
                        Task temp = contents.get(x - 1);
                        temp.markedDone();
                        System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                                "      " + temp.getStatusIcon() + " " + temp.getDescription() + "\n    ***\n");
                    } else {
                        String index = userInput.substring(7);
                        int x = Integer.parseInt(index);
                        Task temp = contents.get(x - 1);
                        contents.remove(temp);
                        System.out.println("    ***\n" + "    You have successfully removed this task:\n" +
                                "      " + temp.getStatusIcon() + " " + temp.getDescription() + "\n    ***\n");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Requires a number.");
                } catch (ArrayIndexOutOfBoundsException f) {
                    System.out.println("Invalid number given. Number is larger than list count.");
                }
            } else {
                Task newTask;
                if (userInput.startsWith("deadline")) {
                    newTask = new Deadline(userInput);
                } else if (userInput.startsWith("event")) {
                    newTask = new Event(userInput);
                } else if (userInput.startsWith("todo")) {
                    newTask = new ToDos(userInput);
                } else {
                    throw new IllegalArgumentException("Invalid input. \nYou may only use the following inputs: " +
                            "(bye, list, done, deadline, event, todo) and any text thereafter.");
                }
                contents.add(newTask);
                if (contents.size() == 1) {
                    System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                        newTask.printTask() + "\n    You now have " + contents.size() + " task in the list.\n" +
                        "    ***\n");
                } else {
                    System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                            newTask.printTask() + "\n    You now have " + contents.size() + " tasks in the list.\n" +
                            "    ***\n");
                }
            }
            userInput = newScan.nextLine();
        }
        System.out.println("\nGoodbye! Have a nice day. :)");
        System.exit(0);
    }
}