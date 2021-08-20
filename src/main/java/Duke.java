import java.util.*;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static final String SEPARATOR = "__________________________________________\n";
    private static final String GREETING = "Hello! I'm Talky McTalkFace\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String PROMPT = "What would you like to do?";
    private static final String TASK_DONE = "Nice! I've marked this task as done:\n";
    private static final String PRINT_LIST = "Here are the tasks in your list:";
    private static final String ADD_TASK_PREFIX = "Got it. I've added this task:\n";

    public static void prompt() {
        String ExitCommand = "bye";
        String ListCommand = "list";
        String DoneCommand = "done ";
        String ToDoCommand = "todo";
        String DeadlineCommand = "deadline";
        String EventCommand = "event";
        String input = "";

        while (!input.equals(ExitCommand)) {
            System.out.println(PROMPT);
            input = sc.nextLine();
            if (input.equals(ExitCommand)) {
                exit();
            } else if (input.equals(ListCommand)) {
                printList(taskList);
            } else if (input.startsWith(DoneCommand)) {
                String taskNumber = input.substring(5);
                taskDone(taskNumber);
            } else if (input.startsWith(ToDoCommand)) {
                addTodo(input);
            } else if (input.startsWith(DeadlineCommand)) {
                addDeadline(input);
            } else if (input.startsWith(EventCommand)) {
                addEvent(input);
            } else {
                System.out.println(input);
            }
        }
    }

    public static void exit() {
        System.out.print(SEPARATOR + EXIT + SEPARATOR);
    }

    public static void printList(List<Task> taskList) {
        System.out.print(SEPARATOR);
        System.out.println(PRINT_LIST);
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
        System.out.print(SEPARATOR);
    }

    public static void taskDone(String taskNumber) {
        String invalid = "Please enter a valid task number.";

        try {
            int taskNo = Integer.parseInt(taskNumber);
            Task task = taskList.get(taskNo - 1);
        } catch (Exception e) {
            System.out.println(invalid);
            prompt();
        } finally {
            Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
            task.setDone();
            System.out.print(SEPARATOR + TASK_DONE + task.toString() + "\n" + SEPARATOR);
        }
    }

    public static void addTaskSuffix(Task task) {
        int taskNo = taskList.size();
        String t = taskNo == 1 ? " task " : " tasks ";
        System.out.print(SEPARATOR
                + ADD_TASK_PREFIX
                + task.toString()
                + "\n"
                + "Now you have "
                + taskNo
                + t
                + "left in the list.\n"
                + SEPARATOR);
    }

    public static void addTodo(String input) {
        Task newTodo = new Todo(input);
        taskList.add(newTodo);
        addTaskSuffix(newTodo);
    }

    public static void addDeadline(String input) {
        Task newDeadline = new Deadline(input);
        taskList.add(newDeadline);
        addTaskSuffix(newDeadline);
    }

    public static void addEvent(String input) {
        Task newEvent = new Event(input);
        taskList.add(newEvent);
        addTaskSuffix(newEvent);
    }

    public static void main(String[] args) {
        System.out.print(SEPARATOR + GREETING + SEPARATOR);
        prompt();
    }
}
