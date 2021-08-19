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

    private static final String FAREWELL_TEXT = "\t☹☹☹ OOPS!!! Why do you choose to leave me!";

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

    public static String wrapAsMessage(String text) {
        return "\t" + HORIZONTAL_LINE + "\n" + text + "\n\t" + HORIZONTAL_LINE;
    }

    private static void printAddedTask(Task task) {
        String addMessage = "\tGot it. I've added this task:\n\t\t"
                + task
                + "\n\tTask(s) remaining in the list: "
                + Duke.numOfTasks;
        System.out.println(Duke.wrapAsMessage(addMessage));
    }

    public static void applyCommand(String command) throws DukeException {
        if (command.contains(" ")) {
            String commandType = command.split(" ", 2)[0];
            String description = command.split(" ", 2)[1].trim();

            switch (commandType) {
                case "todo": {
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task task = new ToDo(description);
                        Duke.addTask(task);
                        printAddedTask(task);
                    }
                    return;
                }
                case "deadline": {
                    if (description.contains("/by")) {
                        String[] information = description.split("/by ");
                        Task task = new Deadline(information[0], information[1]);
                        Duke.addTask(task);
                        printAddedTask(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! You did not provide the time." +
                                "\n\t Please use the command /by");
                    }
                    return;
                }
                case "event": {
                    if (description.contains("/at")) {
                        String[] information = description.split("/at ");
                        Task task = new Event(information[0], information[1]);
                        Duke.addTask(task);
                        printAddedTask(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! You did not provide the time." +
                                "\n\t Please use the command /at");
                    }
                    return;
                }
                case "done": {
                    if (description.matches("\\d+")) {
                        int item = Integer.parseInt(description);
                        if (item == 0) {
                            throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                        }
                        if (item > numOfTasks) {
                            throw new DukeException("☹ OOPS!!! The item number is out of bound!");
                        }
                        if (Duke.tasks[item - 1].isDone) {
                            throw new DukeException("☹ OOPS!!! The task is already done!");
                        }
                        Duke.tasks[item - 1].setDone(true);
                        String doneMessage = "\tNice! I've marked this task as done:\n\t\t"
                                + Duke.tasks[item - 1];
                        System.out.println(wrapAsMessage(doneMessage));
                    } else {
                        throw new DukeException("☹ OOPS!!! The item should be an positive integer.");
                    }
                    return;
                }
                default: {
                    throw new DukeException("☹ OOPS!!! I don't understand that!");
                }
            }
        }

        switch (command) {
            case "":
                throw new DukeException("Your command cannot be empty!");
            case "list":
                String listMessage = Duke.wrapAsMessage(Duke.printList());
                System.out.println(listMessage);
                break;
            case "bye":
                break;
            default:
                throw new DukeException("☹ OOPS!!! I don't understand that!");
        }
    }

    public static void main(String[] args) {
        String greetingMessage = Duke.wrapAsMessage(GREETING_TEXT);
        String byeMessage = Duke.wrapAsMessage(FAREWELL_TEXT);

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println(greetingMessage);

        String input = scanner.next();

        while (!input.equals("bye")) {
            try {
                Duke.applyCommand(input);
            } catch (DukeException e) {
                System.out.println(Duke.wrapAsMessage("\t" + e.getMessage()));
            } finally {
                input = scanner.next();
            }
        }

        System.out.println(byeMessage);
    }
}
