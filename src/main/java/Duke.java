import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initial values
        String sepLine = "____________________________________________________________";
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);

        String start = "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + sepLine;

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(start);

        // Main loop for commands
        while (isRunning) {
            // split the input string into two parts:
            // first part is the command (list, to-do, deadline, etc.)
            // second part is any extra information based on what command was given
            String[] next = sc.nextLine().split(" ", 2);
            String command = next[0];
            if (command.equals("bye")) {
                System.out.println(sepLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + sepLine);
                isRunning = false;
            } else if (command.equals("list")) {
                System.out.println(sepLine);
                System.out.println("These are your tasks! \n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(i + 1 + ". " + taskList.get(i) + "\n");
                }
                System.out.println(sepLine);
            } else if (command.equals("done")) {
                System.out.println(sepLine);
                try {
                    // Mark a task as done
                    // We assume the command is of the form "done xxx" where xxx is an integer
                    Integer taskNum = Integer.parseInt(next[1]);
                    Task taskToComplete = taskList.get(taskNum - 1);
                    taskToComplete.markAsDone();
                    System.out.println("The task has been marked as done!");
                    System.out.println(taskToComplete);
                    System.out.println(sepLine);
                } catch (NumberFormatException e) {
                    System.out.println("It seems like you have entered an invalid number for done.");
                    System.out.println("Please enter a whole number.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("That number does not exist in your list of tasks.");
                }
            } else if (command.equals("help")) {
                String helpMessage = sepLine + "\n HELP \n" + sepLine + "\n"
                        + "Available commands: \n"
                        + "'help' - Opens this dialog. \n"
                        + "'list' - Opens your list of tasks. \n"
                        + "'todo (desc)' - Adds a todo item with the given description. \n"
                        + "'deadline (desc) /by (due date)' - Adds a deadline item to your task list "
                        + "with the given description and due date. \n"
                        + "'event (desc) /at (timing)' - Adds a event item to your task list "
                        + "with the given description and timing. \n"
                        + "'done (x)' - Marks the task with number x as done"
                        + "(according to the list given by the command 'list' \n"
                        + "'bye' - Quits this program. \n"
                        + sepLine + "\n"
                        + "To use any command, follow the structure as shown, entering your values \n"
                        + "in place of anything in brackets. \n"
                        + sepLine;
                System.out.println(helpMessage);
            } else {
                try {
                    if (command.equals("todo")) {
                        Todo todo = new Todo(next[1]);
                        taskList.add(todo);
                        System.out.println(sepLine + "\n added: " + todo + "\n");
                        System.out.println("You now have " + taskList.size() + " tasks");
                        System.out.println(sepLine);
                    } else if (command.equals("deadline")) {
                        // Add a deadline to the task list
                        String[] text = next[1].split("/by ");
                        try {
                            String desc = text[0];
                            String dueDate = text[1];
                            Deadline dl = new Deadline(desc, dueDate);
                            taskList.add(dl);
                            System.out.println(sepLine + "\n added: " + dl + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        }
                    } else if (command.equals("event")) {
                        // Add an event to the task list
                        String[] text = next[1].split("/at ");
                        try {
                            String desc = text[0];
                            String time = text[1];
                            Event event = new Event(desc, time);
                            taskList.add(event);
                            System.out.println(sepLine + "\n added: " + event + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        }
                    } else {
                        System.out.println(sepLine + "\n I did not understand that command."
                                + " Type 'help' for more info \n" + sepLine);
                    }
                } catch (MissingFieldException e) {
                    System.out.println("Please fill in a timing for your deadline / event.");
                } catch (EmptyDescException e) {
                    System.out.println("Please fill in a description for your task.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("It seems like your command was not formatted properly.");
                }
            }
        }
        sc.close();
    }
}
