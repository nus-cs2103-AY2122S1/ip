import java.util.Scanner;

public class Duke {
    private static final String LOGO = "\t____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING_TEXT = "\tHello from \n"
            + LOGO
            + "\tHow can I help you?";

    private static final String FAREWELL_TEXT = "\tBye! Thank you for chatting with me!";

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final Task[] tasks = new Task[100];

    private static int numOfTasks = 0;

    public static void addTask(Task task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
    }

    public static String printList() {
        StringBuilder itemList = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < numOfTasks; i++) {
            itemList.append("\t").append(i + 1).append(". ").append(tasks[i]);
            if (i < numOfTasks - 1) {
                itemList.append("\n");
            }
        }

        return itemList.toString();
    }

    private static String wrapAsMessage(String text) {
        return "\t" + HORIZONTAL_LINE + "\n" + text + "\n\t" + HORIZONTAL_LINE;
    }

    public static void main(String[] args) {
        String greetingMessage = Duke.wrapAsMessage(GREETING_TEXT);
        String byeMessage = Duke.wrapAsMessage(FAREWELL_TEXT);

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println(greetingMessage);

        String input = scanner.next();

        while (!input.equals("bye")) {
            String[] inputSplit = input.split(" ", 2);
            if (input.equals("list")) {
                String listMessage = Duke.wrapAsMessage(Duke.printList());
                System.out.println(listMessage);
            } else if (inputSplit[0].equals("done")) {
                int item = Integer.parseInt(inputSplit[1]);
                Duke.tasks[item - 1].setDone(true);
                String doneMessage = "\tNice! I've marked this task as done:\n\t\t"
                        + Duke.tasks[item - 1];
                System.out.println(wrapAsMessage(doneMessage));
            } else {
                Task task = null;
                if (inputSplit[0].equals("todo")) {
                    task = new ToDo(inputSplit[1]);
                } else if (inputSplit[0].equals("deadline")) {
                    String[] information = inputSplit[1].split("/by ");
                    task = new Deadline(information[0], information[1]);
                } else if (inputSplit[0].equals("event")) {
                    String[] information = inputSplit[1].split("/at ");
                    task = new Event(information[0], information[1]);
                }
                Duke.addTask(task);
                String addMessage = "\tGot it. I've added this task:\n\t\t"
                        + task
                        + "\n\tTask(s) remaining in the list: "
                        + Duke.numOfTasks;
                System.out.println(Duke.wrapAsMessage(addMessage));
            }
            input = scanner.next();
        }

        System.out.println(byeMessage);
    }
}
