import java.util.Scanner;

public class Duke {
    private static final String LINE = "----------------------------------------------------";
    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    private static Scanner scanner = new Scanner(System.in);
    public Duke(){}

    public static void addTaskToList(Task task) {
        taskList[counter] = task;
        System.out.printf("added: " + task.toString()
                + "\nNow you have %s tasks in your list\n" , counter + 1);
        counter++;
    }

    public static void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) break;
            System.out.printf("\t%s." + taskList[i].toString() + "%n", i + 1);
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
                        taskList[doneNumber].setDone();
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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        dukeAction();
    }
}
