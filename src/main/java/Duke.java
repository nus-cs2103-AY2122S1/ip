import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String WelcomeMessage = "   _____________________________________\n"
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "   _____________________________________\n";

    private static String ByeMessage = "   _____________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "   _____________________________________\n";

    public static void newToDo(String taskName, TaskList tList) {
        ToDo newToDo = new ToDo(taskName);
        tList.addTask(newToDo);
        String toDoMessage = "   _____________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newToDo.showType() + newToDo.checkDone() + " " + newToDo.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "   _____________________________________\n";
        System.out.println(toDoMessage);
    }

    public static void newDeadline(String taskName, TaskList tList) {
        Deadline newDeadline = new Deadline(taskName);
        tList.addTask(newDeadline);
        String deadlineMessage = "   _____________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newDeadline.showType()
                            + newDeadline.checkDone() + " "
                            + newDeadline.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "   _____________________________________\n";
        System.out.println(deadlineMessage);
    }

    public static void newEvent(String taskName, TaskList tList) {
        Event newEvent = new Event(taskName);
        tList.addTask(newEvent);
        String eventMessage = "   _____________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newEvent.showType()
                + newEvent.checkDone() + " "
                + newEvent.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "   _____________________________________\n";
        System.out.println(eventMessage);
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo + WelcomeMessage);

        TaskList taskList = new TaskList(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            String[] instruction = input.split(" ", 2);
            if (instruction[0].equals("todo")) {
                newToDo(instruction[1], taskList);
                input = sc.nextLine();
                continue;
            }

            if (instruction[0].equals("deadline")) {
                newDeadline(instruction[1], taskList);
                input = sc.nextLine();
                continue;
            }
            if (instruction[0].equals("event")) {
                newEvent(instruction[1], taskList);
                input = sc.nextLine();
                continue;
            }

            if (instruction[0].equals("done")) {
                int taskIndex = Integer.parseInt(instruction[1]);
                Task taskItem = taskList.extractTask(taskIndex - 1);
                taskItem.isDone();
                System.out.println(taskItem.taskDone());
                input = sc.nextLine();
                continue;
            }

            if (input.equals("list")) {
                taskList.showList();
                input = sc.nextLine();
                continue;
            }
        }

        System.out.println(ByeMessage);
    }
}
