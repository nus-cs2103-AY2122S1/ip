import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description:
 * Duke the ChatBot allows users to add 3 different types of tasks, mark them as done, and delete tasks.
 * The commands for usage are as follows:
 * 1. "todo <name>" where name is what the user would like the todo to be called.
 * 2. "event <name>" where name is what the user would like the event to be called.
 * 3. "deadline <name>" where name is what the user would like the deadline to be called.
 * 4. "list" to view current tasks added to the tasks list.
 * 5. "done <number>" where number is the task with the corresponding number in the list which the user would like to mark as completed.
 * 6. "delete <number>" where number is the task with the corresponding number in the list which the user would like to remove.
 * 7. "bye" to leave the ChatBot.
 * Disclaimer: any other commands will not be recognised and user will be prompted to enter a valid command.
 *
 * @author Leong Hong Fai
 */

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
        commands();
    }

    /**
     * Prints out text to say goodbye to user.
     */
    private static void byeCommand() {
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
    }

    /**
     * Prints out the current list of tasks the user has.
     *
     * @param command entered by user.
     * @throws DukeException upon invalid commands or empty tasks list.
     */
    private static void printList(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            throw new DukeException("invalidCommand");
        }
        else if (storage.isEmpty()) {
            throw new DukeException("noTasksException");
        } else {
            System.out.println("    ______________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, storage.get(i).toString());
            }
            System.out.println("    ______________________________________");
        }
    }

    /**
     * Adds a ToDo to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addToDo(String command) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("invalidToDo");
        } else {
            String name = command.substring(5);
            Task task  = new ToDo(name);
            storage.add(task);
            System.out.println("    ______________________________________");
            System.out.println("     Got it. I've added this task: ");
            System.out.printf("       %s\n",task);
            System.out.printf("     Now you have %d tasks in the list\n", storage.size());
            System.out.println("    ______________________________________");
        }

    }


    /**
     * Adds a deadline to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addDeadline(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidDeadline");
        }
         else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        }
        else {
            int position = command.indexOf("/by");
            String name = command.substring(9, position);
            String date = command.substring(position + 4);
            Task task  = new Deadline(name, date);
            storage.add(task);
            System.out.println("    ______________________________________");
            System.out.println("     Got it. I've added this task: ");
            System.out.printf("       %s\n",task);
            System.out.printf("     Now you have %d tasks in the list\n", storage.size());
            System.out.println("    ______________________________________");
        }
    }


    /**
     * Adds event to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addEvent(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidEvent");
        }
        else if (!command.contains("/at")) {
            throw new DukeException("invalidEvent");
        }
        else {
            int position = command.indexOf("/at");
            String name = command.substring(6, position);
            String date = command.substring(position + 4);
            Task task  = new Event(name, date);
            storage.add(task);
            System.out.println("    ______________________________________");
            System.out.println("     Got it. I've added this task: ");
            System.out.printf("       %s\n",task);
            System.out.printf("     Now you have %d tasks in the list\n", storage.size());
            System.out.println("    ______________________________________");
        }
    }

    /**
     * Marks a specific task in the list as completed.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    private static void markCompleted(String command) throws DukeException {
        String restOfCommand = command.substring(5);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < storage.size()) {
                Task currTask = storage.get(taskNum);
                currTask.setCompleted();
                System.out.println("    ______________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.printf("       %s\n", currTask);
                System.out.println("    ______________________________________");
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormat");
        }
    }


    /**
     * Deletes a specified task from the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    private static void deleteTask(String command) throws DukeException {
        String restOfCommand = command.substring(7);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < storage.size()) {
                Task currTask = storage.get(taskNum);
                storage.remove(taskNum);
                System.out.println("    ______________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.printf("       %s\n", currTask);
                System.out.printf("     Now you have %d tasks in the list.\n", storage.size());
                System.out.println("    ______________________________________");
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormatDelete");
        }
    }

    private static void commands() throws DukeException {
        int pointer = 1;
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] words = command.split(" ");
            String init = words[0];
            if (!command.equals("bye")) {
                try {
                    switch (init) {
                        case ("list"):
                            printList(command);
                            break;
                        case ("todo"):
                            addToDo(command);
                            break;
                        case ("deadline"):
                            addDeadline(command);
                            break;
                        case ("event"):
                            addEvent(command);
                            break;
                        case ("done"):
                            markCompleted(command);
                            break;
                        case ("delete"):
                            deleteTask(command);
                            break;
                        default:
                            throw new DukeException("invalidCommand");
                    }
                } catch (DukeException err) {
                    System.out.println("    ______________________________________");
                    System.out.printf("     %s\n", err);
                    System.out.println("    ______________________________________");
                }
            }
            else {
                byeCommand();
                scanner.close();
                break;
            }
        }
    }




}
