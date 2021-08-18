import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?");

        Scanner newScan = new Scanner(System.in);
        ArrayList<Task> contents = new ArrayList<>();
        boolean canRun = true;

        while (userInput) {
            String userInput = newScan.nextLine().toLowerCase();
            if (userInput.contains("bye")) {
                System.out.println("\nGoodbye! Have a nice day. :)");
                System.exit(0);
            } else if (userInput.contains("list"):
            int counter = 1;
                    System.out.println("    ***\n" + "    These are your tasks in the list:");
                    for (Task y: contents) {
                        System.out.println("      " + counter + ". " + y.printTask());
                        counter++;
                    }
                    System.out.println("    ***\n");
                    break;

                case "done ":
                    String index = userInput.substring(5);
                    int y = Integer.parseInt(index);
                    Task temp = contents.get(y - 1);
                    temp.markedDone();
                    System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                            "      " + y + ". " + temp.printTask() + "\n    ***\n");
                    break;

                case "deadline ":
                    Task newTask = new Deadline(userInput);
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
                    break;

                case "event ":
                    newTask = new Event(userInput);
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
                    break;

                case "todo ":
                    newTask = new ToDos (userInput);
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
                    break;

                default:
                    newTask = new Task(userInput);
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
                    break;
            }
        }

    }
}