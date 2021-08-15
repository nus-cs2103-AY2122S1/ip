import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();

        String line = "-----------------------------------------------------------------------------------\n\n";
        // greeting
        System.out.print(
                line +
                "\tHello! I'm Duke\n\n" +
                "\tWhat can I do for you?\n\n" +
                line
        );

        Scanner scanner = new Scanner(System.in);
        try {
            String task;
            String text;
            boolean leave = false;
            while (!leave) {
                // get task
                task = scanner.next();
                // get text
                text = scanner.nextLine().trim();

                switch(task) {
                    case "bye":
                        text = "Bye. Hope to see you again soon!";
                        leave = true;
                        break;
                    case "list":
                        if (taskList.isEmpty()) text = "There are no items in the list.";
                        else {
                            text = "";
                            for (int i = 0; i < taskList.size(); i++) {
                                if (i != 0) text += "\t";
                                text += (i + 1) + "." + taskList.get(i).toString();
                                if (i != taskList.size() - 1) text += "\n";
                            }
                        }
                        break;
                    case "done":
//                        System.out.print(text);
                        try {
                            int index = Integer.parseInt(text);
                            taskList.get(index - 1).complete();
                            text = "Nice! I've marked this task as done:\n" +
                                    "\t\t" + taskList.get(index - 1).toString();
                        } catch(NumberFormatException e) {
                            text = "To complete a task: enter \"done (task number)\"";
                        } catch (IndexOutOfBoundsException e) {
                            text = "Task number does not exist.";
                        }
                        break;
                    case "todo":
                        ToDo toDo = new ToDo(text);
                        taskList.add(toDo);
                        text = "Got it. I've added this task:\n" +
                            "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                            "\t" + "Now you have " + taskList.size() + " tasks in the list.";
                        break;
                    case "deadline":
                        int byIndex = text.indexOf("/by");
                        if (byIndex == -1 || text.substring(byIndex).length() < 4) {
                            text = "To create a deadline task: enter \"deadline (task details) /by (deadline)\"";
                        } else {
                            String details = text.substring(0, byIndex - 1);
                            String by = text.substring(byIndex + 4);
                            Deadline deadline = new Deadline(details, by);
                            taskList.add(deadline);
                            text = "Got it. I've added this task:\n" +
                                    "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                                    "\t" + "Now you have " + taskList.size() + " tasks in the list.";
                        }
                        break;
                    case "event":
                        int atIndex = text.indexOf("/at");
                        if (atIndex == -1 || text.substring(atIndex).length() < 4) {
                            text = "To create an event task: enter \"event (task details) /at (event timing)\"";
                        } else {
                            String details = text.substring(0, atIndex - 1);
                            String at = text.substring(atIndex + 4);
                            Event event = new Event(details, at);
                            taskList.add(event);
                            text = "Got it. I've added this task:\n" +
                                    "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                                    "\t" + "Now you have " + taskList.size() + " tasks in the list.";
                        }
                        break;
                    default:
                        text = "Invalid instruction.";
                }

                // print text
                System.out.print(
                        "\n" + line +
                        "\t" + text + "\n" +
                        "\n" + line
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println(e);
        }
    }
}
