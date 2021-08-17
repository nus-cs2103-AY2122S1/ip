import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Task[] store = new Task[100];
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

    private static void byeCommand() {
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
    }
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

    private static void addDeadline(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidDeadline");
        }
         else if (!words[2].equals("/by")) {
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

    private static void addEvent(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidEvent");
        }
        else if (!words[2].equals("/at")) {
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

    private static void commands() throws DukeException {
        store = new Task[100];
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
                        case("done"):
                            markCompleted(command);
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
