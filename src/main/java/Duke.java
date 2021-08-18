import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "----------------------------------------------------";
    private static final String COMMANDS = LINE + "-------------------------\n" +
            "|\tPlease enter one of the following commands:                             |\n" +
            "|\t1. todo <description> (eg. todo paint)                                  |\n" +
            "|\t2. deadline <description> /by <time> (e.g deadline submit hw /by 6pm)   |\n" +
            "|\t3. event <description> /at <time> (e.g event party /at 8pm)             |\n" +
            "|\t4. list - see list of tasks added                                       |\n" +
            "|\t5. delete <task number> (e.g delete 1) - delete a task from list        |\n" +
            "|\t6. done <task number> (e.g done 1) - mark a task in list as done        |\n" +
            "|\t7. bye - exit duke                                                      |\n" +
            LINE + "-------------------------\n";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public Duke(){}

    public static void addTaskToList(Task task) {
        taskList.add(task);
        System.out.printf("added: " + task.toString()
                + "\nNow you have %s tasks in your list\n" , taskList.size());
    }

    public static void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t%s."+ taskList.get(i).toString() + "%n", i + 1);
        }
    }

    public static String[] getDescriptionAndTime(String str,
                                          String taskType) throws DukeException {
        String[] next = scanner.nextLine().split(str);
        if (next.length < 2) {
            if (next[0].trim().length() == 0) {
                throw new NoDescriptionAndTimeException(taskType);
            } else {
                throw new NoTimeException(taskType);
            }
        }
        return next;
    }

    public static void addDeadlineToList() throws DukeException {
        String[] taskTimeD = getDescriptionAndTime("/by","deadline");
        Deadline deadline = new Deadline(taskTimeD[0].trim(), taskTimeD[1].trim());
        addTaskToList(deadline);
    }

    public static void addEventToList() throws DukeException {
        String[] taskTimeE = getDescriptionAndTime("/at", "event");
        Event event = new Event(taskTimeE[0].trim(), taskTimeE[1].trim());
        addTaskToList(event);
    }

    public static void addTodoToList() throws DukeException {
        Todo todo = new Todo(scanner.nextLine().trim());
        addTaskToList(todo);
    }

    public static void deleteFromList() throws DukeException {
        int deleteNumber = Integer.parseInt(scanner.next()) - 1;
        if (deleteNumber < 0 || deleteNumber > taskList.size()-1) {
            throw new InvalidTaskDeletion();
        }
        Task task = taskList.get(deleteNumber);
        taskList.remove(deleteNumber);
        System.out.printf("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , taskList.size());
    }


    public static void dukeAction() {
        String inp = scanner.next();
        while(!inp.equals("bye")) {
            try {
                System.out.println(LINE);
                switch (inp) {
                    case "list":
                        printTasksInList();
                        break;

                    case "done":
                        int doneNumber = Integer.parseInt(scanner.next()) - 1;
                        taskList.get(doneNumber).setDone();
                        break;

                    case "todo":
                        addTodoToList();
                        break;

                    case "deadline":
                        addDeadlineToList();
                        break;

                    case "event":
                        addEventToList();
                        break;

                    case "delete":
                        deleteFromList();
                        break;

                    default:
                        scanner.nextLine();
                        throw new NoCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
                inp = scanner.next();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n"
                + COMMANDS);
        dukeAction();
    }
}
