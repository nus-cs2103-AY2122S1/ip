import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();

        String line = "------------------------------------------------------------------------------" +
                        "-------------------------------\n\n";
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

                try {
                    switch (task) {
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
                            try {
                                int index = Integer.parseInt(text);
                                taskList.get(index - 1).complete();
                                text = "Nice! I've marked this task as done:\n" +
                                        "\t\t" + taskList.get(index - 1).toString();
                            } catch (NumberFormatException e) {
                                throw new InvalidInputException("To complete a task: enter \"done (task number)\"");
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidInputException("Task number does not exist.");
                            }
                            break;
                        case "delete":
                            try {
                                int index = Integer.parseInt(text);
                                text = "Noted. I've removed this task: \n" +
                                        "\t\t" + taskList.get(index - 1).toString() + "\n" +
                                        "\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
                                taskList.remove(index - 1);
                            } catch (NumberFormatException e) {
                                throw new InvalidInputException("To delete a task: enter \"delete (task number)\"");
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidInputException("Task number does not exist.");
                            }
                            break;
                        case "todo":
                            if (text.length() == 0) {
                                throw new InvalidInputException("ToDo task needs a description.");
                            } else {
                                ToDo toDo = new ToDo(text);
                                taskList.add(toDo);
                                text = "Got it. I've added this task:\n" +
                                        "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                                        "\t" + "Now you have " + taskList.size() + " tasks in the list.";
                            }
                            break;
                        case "deadline":
                            int byIndex = text.indexOf("/by");
                            if (byIndex == -1) {
                                throw new InvalidInputException(
                                        "To create a deadline task: enter \"deadline (task details) /by (deadline)\""
                                );
                            } else {
                                String details = text.substring(0, byIndex).trim();
                                if (details.length() == 0)
                                    throw new InvalidInputException("Deadline task needs a description.");
                                else if (text.substring(byIndex).trim().length() <= 3)
                                    throw new InvalidInputException("Deadline task needs a deadline.");
                                else {
                                    String by = text.substring(byIndex + 3).trim();
                                    Deadline deadline = new Deadline(details, by);
                                    taskList.add(deadline);
                                    text = "Got it. I've added this task:\n" +
                                            "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                                            "\t" + "Now you have " + taskList.size() + " tasks in the list.";

                                }
                            }
                            break;
                        case "event":
                            int atIndex = text.indexOf("/at");
                            if (atIndex == -1) {
                                throw new InvalidInputException(
                                        "To create an event task: enter \"event (task details) /at (event timing)\""
                                );
                            } else {
                                String details = text.substring(0, atIndex).trim();
                                if (details.length() == 0)
                                    throw new InvalidInputException("Event task needs a description.");
                                else if (text.substring(atIndex).trim().length() <= 3)
                                    throw new InvalidInputException("Event task needs an event timing.");
                                else {
                                    String by = text.substring(atIndex + 3).trim();
                                    Event event = new Event(details, by);
                                    taskList.add(event);
                                    text = "Got it. I've added this task:\n" +
                                            "\t\t" + taskList.get(taskList.size() - 1).toString() + "\n" +
                                            "\t" + "Now you have " + taskList.size() + " tasks in the list.";

                                }
                            }
                            break;
                        default:
                            throw new InvalidInstructionException(task);
                    }
                } catch (InvalidInputException e) {
                    text = e.toString();
                } catch (InvalidInstructionException e) {
                    text = e.toString();
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
