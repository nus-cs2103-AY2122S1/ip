import java.util.Scanner;
import java.util.ArrayList;

/**
 * This program is a chatbot that helps keep track of various tasks.
 *
 * @author Lethicia Renissa Santoso (G12)
 */
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Scanner scan = new Scanner(System.in);

    /**
     * The program reads commands given by the user to perform different
     * task tracking functionalities.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Duke.greet();
        String input = scan.nextLine();

        while (!input.equals("bye")) {
            System.out.println("_______________________");
            try {
                if (input.equals("list")) {
                    Duke.listTasks();
                } else if (input.matches("done (.*)")) {
                    String index = input.substring(5);
                    Duke.doneTask(index);
                } else if (input.matches("delete (.*)")) {
                    String index = input.substring(7);
                    Duke.deleteTask(index);
                } else if (input.matches("todo(.*)") || input.matches("event(.*)") ||
                        input.matches("deadline(.*)")) {
                    Duke.addTask(input);
                } else {
                    throw new DukeException("I'm sorry, but I don't understand what that means :(");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("_______________________");
            input = scan.nextLine();
        }
        Duke.exit();
    }

    /**
     * Greet user upon running the chatbot.
     */
    private static void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you need to do today?");
        System.out.println("_______________________");
    }

    /**
     * Close the chatbot when user is done.
     */
    private static void exit() {
        System.out.println("_______________________");
        System.out.println("See you! Have a nice day!");
        System.out.println("_______________________");
        scan.close();
    }

    /**
     * List the current task list.
     */
    private static void listTasks() {
        System.out.println("Here's your to do list:");
        int j = 0;
        for (Task item : tasks) {
            System.out.printf("%d. %s%n", j + 1, tasks.get(j));
            j += 1;
        }
    }

    /**
     * Add a given task into the tracking list.
     *
     * @param description The command entered by user.
     * @throws DukeException Throws if command given is not valid.
     */
    private static void addTask(String description) throws DukeException {
        if (description.matches("todo(.*)")) {
            try {
                String taskDesc = description.substring(5);
                tasks.add(new ToDo(taskDesc));
            } catch (StringIndexOutOfBoundsException e){
                throw new DukeException("The todo task description cannot be empty. " +
                        "Please use format todo <desc>");
            }
        } else if (description.matches("event(.*)")) {
            try {
                String taskDesc = description.substring(6);
                String[] fields = taskDesc.split(" /at ", 2);
                tasks.add(new Event(fields[0], fields[1]));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The Event description cannot be empty. " +
                        "Please use format event <description> /at <time>.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use format event <description> /at <time>.");
            }
        } else if (description.matches("deadline(.*)")) {
            try {
                String taskDesc = description.substring(9);
                String[] fields = taskDesc.split(" /by ", 2);
                tasks.add(new Deadline(fields[0], fields[1]));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The Deadline description cannot be empty. " +
                        "Please use format deadline <description> /by <time>.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use format deadline <description> /by <time>.");
            }
        }
        System.out.printf("added: %s%n", tasks.get(tasks.size() - 1));
        System.out.printf("Now you have %d tasks in your list.%n", tasks.size());

    }

    /**
     * Delete a task from the current task list.
     *
     * @param taskIndex the task no. to be deleted.
     * @throws DukeException if command or no. given is not valid.
     */
    private static void deleteTask(String taskIndex) throws DukeException {
        try {
            int i = Integer.parseInt(taskIndex) - 1;
            Task deleted = tasks.get(i);
            tasks.remove(i);
            System.out.println("Got it! I've removed this task:");
            System.out.println(deleted);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please use the format delete <task No.>");
        } catch (IndexOutOfBoundsException indexError) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }
    }

    /**
     * Mark a task in the current list as done.
     *
     * @param taskIndex the task no. to be marked done.
     * @throws DukeException if command or no. given is not valid.
     */
    private static void doneTask(String taskIndex) throws DukeException {
        try {
            int i = Integer.parseInt(taskIndex) - 1;
            tasks.get(i).markDone();
            System.out.println("Good job! I've marked this task as done:");
            System.out.println(tasks.get(i));
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please use the format done <task No.>");
        } catch (IndexOutOfBoundsException indexError) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }
    }

}
